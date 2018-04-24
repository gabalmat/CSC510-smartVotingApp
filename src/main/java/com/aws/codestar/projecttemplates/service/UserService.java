package com.aws.codestar.projecttemplates.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aws.codestar.projecttemplates.dao.UserDao;
import com.aws.codestar.projecttemplates.model.User;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;

	public User getUser(final String username) {
		return userDao.getUser(username);
	}

    public void addUser(final User user) {
        userDao.addUser(user);
    }

}
