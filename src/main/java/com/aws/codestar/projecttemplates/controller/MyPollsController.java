package com.aws.codestar.projecttemplates.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.aws.codestar.projecttemplates.model.Poll;
import com.aws.codestar.projecttemplates.model.User;
import com.aws.codestar.projecttemplates.service.PollService;
import com.aws.codestar.projecttemplates.service.UserService;


@Controller
public class MyPollsController{

    @Autowired
    private PollService pollService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/viewMyPolls", method = RequestMethod.GET)
    public String myPolls(ModelMap myPollsModel) {

        SecurityContext context = SecurityContextHolder.getContext();
        String userName = context.getAuthentication().getName();
        int id = userService.getUserId(userName);

        List<Poll> results = pollService.getUserPollsWithCat(id);
        myPollsModel.addAttribute("myPollsCount", results.size());
        myPollsModel.addAttribute("listPolls", results);

        return "viewMyPolls";
    }

}

