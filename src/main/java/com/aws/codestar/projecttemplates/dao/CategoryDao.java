package com.aws.codestar.projecttemplates.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aws.codestar.projecttemplates.mapper.CategoryRowMapper;
import com.aws.codestar.projecttemplates.model.Category;

@Repository
@Transactional
public class CategoryDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public Category getCategory(final int id) {
		Category cat = jdbcTemplate.queryForObject("select * from categories where id = ?", new Object[] {id}, new CategoryRowMapper());
		return cat;
	}
	
	public Category getCategoryByName(final String name) {
		Category cat = jdbcTemplate.queryForObject("select * from categories where name = ?", new Object[] {name}, new CategoryRowMapper());
		return cat;
	}
	
	public void addCategory(final Category cat) {
		jdbcTemplate.update("insert into categories (name, description, user_id) values (?,?,?)",
				new Object[] { cat.getName(), cat.getDescription(), cat.getUserId() });
	}
	
	public List<Category> getCategories() {
		List<Category> cats = jdbcTemplate.query("select * from categories order by name", new CategoryRowMapper());
		return cats;
	}
}
