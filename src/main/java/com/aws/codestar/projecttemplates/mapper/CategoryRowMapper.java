package com.aws.codestar.projecttemplates.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.aws.codestar.projecttemplates.model.Category;

public class CategoryRowMapper implements RowMapper<Category> {
	
	@Override
	public Category mapRow(ResultSet rs, int row) throws SQLException {
		Category category = new Category();
		category.setDescription(rs.getString("description"));
		category.setName(rs.getString("name"));
		category.setUserId(rs.getInt("user_id"));
		
		return category;
	}
}
