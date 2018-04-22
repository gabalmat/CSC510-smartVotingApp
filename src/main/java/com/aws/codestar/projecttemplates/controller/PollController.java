package com.aws.codestar.projecttemplates.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.aws.codestar.projecttemplates.model.Poll;

@Controller
public class PollController {
	
	@RequestMapping(value = "/createPoll", method = RequestMethod.GET)
	public ModelAndView showForm() {
		return new ModelAndView("createpoll", "poll", new Poll());
	}
	
	@RequestMapping(value = "/poll", method = RequestMethod.POST)
	public String submit(@Valid @ModelAttribute("poll") Poll poll, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			return "error";
		}
		
		model.addAttribute("title", poll.getTitle());
		model.addAttribute("description", poll.getDescription());
		
		return "poll";
	}

}
