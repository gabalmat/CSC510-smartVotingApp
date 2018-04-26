package com.aws.codestar.projecttemplates.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.aws.codestar.projecttemplates.model.Poll;
import com.aws.codestar.projecttemplates.model.User;
import com.aws.codestar.projecttemplates.model.Category;
import com.aws.codestar.projecttemplates.service.PollService;
import com.aws.codestar.projecttemplates.service.UserService;
import com.aws.codestar.projecttemplates.service.CategoryService;


@Controller
public class UserProfileController{

    @Autowired
    private PollService pollService;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService catService;

    @RequestMapping(value = "/profile/{userName}")
    public String userProfile(@PathVariable String userName, ModelMap userProfileModel) {

        User user = userService.getUser(userName);
        List<Poll> results = pollService.getUserPolls(user.getUserid());

        userProfileModel.addAttribute("user", user);
        userProfileModel.addAttribute("pollsCount", results.size());
        userProfileModel.addAttribute("listPolls", results);

        return "userProfile";
    }

}

