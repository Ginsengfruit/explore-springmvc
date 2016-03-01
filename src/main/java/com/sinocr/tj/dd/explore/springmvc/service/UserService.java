package com.sinocr.tj.dd.explore.springmvc.service;

import com.sinocr.tj.dd.explore.springmvc.domain.User;

public interface UserService {
	
	public void createUser(User user);
	public User getUserById(String userId);
}
