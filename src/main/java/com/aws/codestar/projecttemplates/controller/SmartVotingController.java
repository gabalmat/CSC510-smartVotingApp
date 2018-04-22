package com.aws.codestar.projecttemplates.controller;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Basic Spring MVC controller that handles all GET requests.
 */
@Controller
@RequestMapping("/")
public class SmartVotingController {

    private final String siteName;

    public SmartVotingController(final String siteName) {
        this.siteName = siteName;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView smartVoting() {
        ModelAndView mav = new ModelAndView("index");
        
        SecurityContext context = SecurityContextHolder.getContext();
        String username = context.getAuthentication().getName();
        
        mav.addObject("siteName", this.siteName);
        mav.addObject("welcomeMessage", "Welcome to SmartVote " + username);

        mav.addObject("createPoll", "Create Poll");
        mav.addObject("viewMyPolls", "View My Polls");
        mav.addObject("searchPolls", "Search Polls");
        
        return mav;
    }

}
