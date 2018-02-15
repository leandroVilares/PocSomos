package com.somos.poc.service;


import java.util.List;

import com.somos.poc.model.ListaSomos;
import com.somos.poc.model.User;

public interface UserService {
	
	User findById(long id);
	
	User findByName(String name);
	
	void saveUser(User user);
	
	void updateUser(User user);
	
	void deleteUserById(long id);

	List<User> findAllUsers();
	List<ListaSomos> findAll();
	void deleteAllUsers();
	
	boolean isUserExist(User user);
	
}
