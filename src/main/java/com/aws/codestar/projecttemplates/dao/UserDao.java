package com.aws.codestar.projecttemplates.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aws.codestar.projecttemplates.mapper.UserRowMapper;
import com.aws.codestar.projecttemplates.model.User;

@Repository
@Transactional
public class UserDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public User getUser(final String username) {
		User user = jdbcTemplate.queryForObject("select * from users where username = ?", new Object[] {username}, new UserRowMapper());
		return user;
	}

    public void addUser(final User user) {
        jdbcTemplate.update("insert into users (username, password, first_name, last_name, email, enabled) values(?,?,?,?,?,true)",
                new Object[] { user.getUsername(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getEmail() });
    }

}
