package com.aws.codestar.projecttemplates.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aws.codestar.projecttemplates.dao.CategoryDao;
import com.aws.codestar.projecttemplates.model.Category;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryDao catDao;
	
	public Category getCategoryByName(final String name) {
		return catDao.getCategoryByName(name);
	}
	
	public void addCategory(final Category cat) {
		catDao.addCategory(cat);
	}
	
	public List<Category> getCategories() {
		return catDao.getCategories();
	}
}
