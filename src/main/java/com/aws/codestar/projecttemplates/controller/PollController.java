package com.aws.codestar.projecttemplates.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aws.codestar.projecttemplates.model.Category;
import com.aws.codestar.projecttemplates.model.Poll;
import com.aws.codestar.projecttemplates.model.User;
import com.aws.codestar.projecttemplates.service.CategoryService;
import com.aws.codestar.projecttemplates.service.PollService;
import com.aws.codestar.projecttemplates.service.UserService;

@Controller
public class PollController {
	
	@Autowired
	private PollService pollService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CategoryService catService;
	
	@RequestMapping("/poll/{id}")
	public String getPoll(@PathVariable int id, ModelMap pollModel) {
		Poll poll = pollService.getPoll(id);
		pollModel.addAttribute("poll", poll);
		
		//Example for retrieving all polls from database
		//List<Poll> polls = pollService.getPolls();
		
		//Example for retrieving polls containing the word 'polo'... only title and description fields will be searched
		//List<Poll> pollsWhere = pollService.getPollsWhere("polo");
		
		return "poll";
	}
	
	@RequestMapping(value = "addPoll")
	public String addPage(ModelMap pollModel) {
		
		// Get list of all categories
		List<Category> categories = catService.getCategories();
		pollModel.addAttribute("categoryList", categories);
		
		return "createpoll";
	}
	
	@RequestMapping(value = "/add/poll", method = RequestMethod.POST)
	public String addPoll(@ModelAttribute("newPoll") Poll submittedPoll, BindingResult result, ModelMap pollModel) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		User user = userService.getUser(userName);
		
		submittedPoll.setUserId(user.getUserid());
		pollService.addPoll(submittedPoll);
		
		pollModel.addAttribute("msg", "Poll added successfully");
		pollModel.addAttribute("poll", submittedPoll);
		return "poll";
	}

}
