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
    public String addUser(
            @RequestParam(value = "username", required = true) String username,
            @RequestParam(value = "password", required = true) String password,
            @RequestParam(value = "lastName", required = false) String lastName, 
            @RequestParam(value = "firstName", required = false) String firstName,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "userId", required = true) String userId,
            ModelMap userModel) {
        
        // User user = new User();
        // user.setUsername(username)
        // user.setPassword(password)
        // user.setLastName(lastName)
        // user.setFirstName(firstName)
        // user.setEmail(email)
        // user.setUserid(userId)
        // userService.addUser(user);

        // userModel.addAttribute("msgGood", "User added successfully");
        userModel.addAttribute("msgBad", "User not added");
        // userModel.addAttribute("user", user);
        return "userCreateResult";
    }

}
