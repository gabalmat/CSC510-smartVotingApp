package com.aws.codestar.projecttemplates.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.DataAccessException;

import com.aws.codestar.projecttemplates.model.Poll;
import com.aws.codestar.projecttemplates.model.User;
import com.aws.codestar.projecttemplates.service.PollService;
import com.aws.codestar.projecttemplates.service.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    
    @RequestMapping(value = "createUser")
    public String addUser() {
        return "createUser";
    }

    @RequestMapping(value = "/userCreateResult", method = RequestMethod.POST)
    public String addUser(@RequestParam(value = "username", required = true) String username,
            @RequestParam(value = "password", required = true) String password,
            @RequestParam(value = "lastName", defaultValue = " ") String lastName, 
            @RequestParam(value = "firstName", defaultValue = " ") String firstName,
            @RequestParam(value = "email", required = true) String email,
            ModelMap userModel) {

        // TODO: validate email? password strength?
        // https://stackoverflow.com/questions/37746428/java-spring-how-to-handle-missing-required-request-parameters
        
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setLastName(lastName);
        user.setFirstName(firstName);
        user.setEmail(email);

        try {
            userService.addUser(user);
            userModel.addAttribute("success", "User added successfully");
            userModel.addAttribute("user", user);
        } catch (DuplicateKeyException e) {
            userModel.addAttribute("userExists", "User already exists");
            String reason = "Message: " + e.getMessage() + " | Root cause: " + e.getRootCause();
            userModel.addAttribute("reason", reason);
        } catch (DataAccessException e) {
            String reason = "Message: " + e.getMessage() + " | Root cause: " + e.getRootCause();
            userModel.addAttribute("failure", reason);
        }

        return "userCreateResult";
    }

}
