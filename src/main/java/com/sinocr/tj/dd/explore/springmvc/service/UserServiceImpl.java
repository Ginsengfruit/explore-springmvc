package com.sinocr.tj.dd.explore.springmvc.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sinocr.tj.dd.explore.springmvc.domain.User;

@Service
public class UserServiceImpl implements UserService {

	private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public void createUser(User user) {
		logger.info("user created!");
	}

	@Override
	public User getUserById(String userId) {
		User user = new User();
		user.setUserId(userId);
		user.setUserName("userName" + userId);
		user.setPassword("password" + userId);
		user.setRealName("realName" + userId);
		return user;
	}

}
