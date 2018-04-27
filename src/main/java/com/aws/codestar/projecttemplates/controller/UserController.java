package com.aws.codestar.projecttemplates.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
    public String createUser() {
        return "createUser";
    }

    @RequestMapping(value = "/userCreateResult", method = RequestMethod.POST)
    public String addUser(@RequestParam(value = "username", required = true) String username,
            @RequestParam(value = "password", required = true) String password,
            @RequestParam(value = "lastName", defaultValue = " ") String lastName, 
            @RequestParam(value = "firstName", defaultValue = " ") String firstName,
            @RequestParam(value = "email", required = true) String email,
            ModelMap userModel) {

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
            userModel.addAttribute("userExists", "Username already exists, try another ones");
            // for debugging only
            // String reason = "Message: " + e.getMessage() + " | Root cause: " + e.getRootCause();
            // userModel.addAttribute("reason", reason);
        } catch (DataAccessException e) {
            String reason = "Message: " + e.getMessage() + " | Root cause: " + e.getRootCause();
            userModel.addAttribute("failure", reason);
        }

        return "userCreateResult";
    }

}
