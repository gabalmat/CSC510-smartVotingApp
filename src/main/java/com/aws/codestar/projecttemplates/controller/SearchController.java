package com.aws.codestar.projecttemplates.controller;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.validation.BindingResult;

import com.aws.codestar.projecttemplates.model.Poll;
import com.aws.codestar.projecttemplates.service.PollService;
import com.aws.codestar.projecttemplates.model.Category;
import com.aws.codestar.projecttemplates.service.CategoryService;


@Controller
public class SearchController{

    @Autowired
    private PollService pollService;

    @Autowired
    private CategoryService catService;

    @RequestMapping(value = "/searchPolls", method = RequestMethod.GET)
    public String searchPolls(ModelMap searchModel) {
        List<Category> categories = catService.getCategories();
        searchModel.addAttribute("criteria", new String());
        searchModel.addAttribute("category", new Category());
        searchModel.addAttribute("categoryList", categories);

        return "searchPolls";
    }

    @RequestMapping(value = "/searchResults", method = RequestMethod.POST)
    public String searchResults(@RequestParam(value = "criteria", required = true) String criteria,
        @ModelAttribute("category") Category cat, BindingResult result, ModelMap resultsModel) {

        String crit = criteria.toLowerCase();
        String catName = catService.getCategory(cat.getId()).getName();

        List<Poll> results = new ArrayList<Poll>();
        if (cat.getId() == 5){
            results = pollService.getPollsWhere(crit);
        } else {
            results = pollService.getPollsWhereWithCat(crit, cat.getId());
        }
        resultsModel.addAttribute("criteriaChoice", crit);
        resultsModel.addAttribute("categoryChoice", catName);
        resultsModel.addAttribute("searchCount", results.size());
        resultsModel.addAttribute("listPolls", results);

        return "searchResults";
    }

}

