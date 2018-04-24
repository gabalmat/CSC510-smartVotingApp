package com.aws.codestar.projecttemplates.controller;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.DataAccessException;

import com.aws.codestar.projecttemplates.model.Category;
import com.aws.codestar.projecttemplates.service.CategoryService;
import com.aws.codestar.projecttemplates.service.UserService;


@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "createCategory")
    public String createCategory() {
        return "createCategory";
    }

    @RequestMapping(value = "/categoryCreateResult", method = RequestMethod.POST)
    public String addCategory(@RequestParam(value = "categoryName", required = true) String categoryName,
            @RequestParam(value = "description", required = true) String description,
            ModelMap categoryModel) {

        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        int user_id = userService.getUserId(userName);

        Category category = new Category();
        category.setName(categoryName);
        category.setDescription(description);
        category.setUserId(user_id);

        try {
            categoryService.addCategory(category);
            categoryModel.addAttribute("success", "Category added successfully");
            categoryModel.addAttribute("category", category);
        } catch (DuplicateKeyException e) {
            categoryModel.addAttribute("categoryExists", "Category already exists");
            String reason = "Message: " + e.getMessage() + " | Root cause: " + e.getRootCause();
            categoryModel.addAttribute("reason", reason);
        } catch (DataAccessException e) {
            String reason = "Message: " + e.getMessage() + " | Root cause: " + e.getRootCause();
            categoryModel.addAttribute("failure", reason);
        }

        return "categoryCreateResult";
    }

}
