package com.aws.codestar.projecttemplates.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.aws.codestar.projecttemplates.model.Poll;
import com.aws.codestar.projecttemplates.service.PollService;


@Controller
public class SearchController{

    @Autowired
    private PollService pollService;

    @RequestMapping(value = "/searchPolls", method = RequestMethod.GET)
    public String searchPolls(ModelMap searchModel) {
        String criteria = new String();
        searchModel.addAttribute("criteria", criteria);
        return "searchPolls";
    }

    @RequestMapping(value = "/searchResults", method = RequestMethod.POST)
    public String searchResults(@RequestParam(value = "criteria", required = true) String criteria, ModelMap resultsModel) {

        String crit = criteria.toLowerCase();
        resultsModel.addAttribute("criteria", crit);
        
        List<Poll> results = pollService.getPollsWhere(crit);
        resultsModel.addAttribute("searchCount", results.size());
        resultsModel.addAttribute("listPolls", results);

        return "searchResults";
    }

}

