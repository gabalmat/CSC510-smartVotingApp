package com.aws.codestar.projecttemplates.controller;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class SearchTopicsController {

    private final String siteName;

    @ModelAttribute("searchCriteria")
       public SearchCriteria setUpUserForm() {
          return new SearchCriteria();
    }

    public SearchTopicsController(final String siteName) {
        this.siteName = siteName;
    }

    @GetMapping("/searchTopics")
    public ModelAndView searchTopics() {
        ModelAndView mav = new ModelAndView("searchTopics");
        
        mav.addObject("siteName", this.siteName);
        mav.addObject("searchTopics", "Search Topics");

        return mav;
    }

    @PostMapping("/searchResults")
    public ModelAndView searchResults(@ModelAttribute("searchCriteria") SearchCriteria criteria, Model model) {
        ModelAndView mav = new ModelAndView("searchCriteria");

        mav.addObject("searchResults", criteria.getCriteria());

        System.out.println("Criteria : " + criteria.getCriteria());

        return mav;
    }

}


class SearchCriteria{

    private String criteria;

    public String getCriteria(){
        return this.criteria;
    } 

    public void setCriteria(String criteria){
        this.criteria = criteria;
    }

}




