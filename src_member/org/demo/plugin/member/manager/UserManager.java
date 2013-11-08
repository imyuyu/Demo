package org.demo.plugin.member.manager;

import java.util.List;

import org.demo.plugin.member.domain.User;

public interface UserManager {
	public Boolean save(User user);
	
	public List<User> findAll();
	
	public User login(User user);
}
