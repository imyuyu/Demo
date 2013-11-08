package org.demo.plugin.member.manager;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import org.demo.plugin.member.dao.UserDao;
import org.demo.plugin.member.domain.User;

public class UserManagerImpl implements UserManager{
	private static final Log log = LogFactory.getLog(UserManagerImpl.class);
	private UserDao userDao;

	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public Boolean save(User user) {
		boolean flag = true;
		try {
			userDao.save(user);
		} catch (Exception e) {
			log.info(e.getMessage(),e);
			flag = false;
		}
		return flag;
	}

	public List<User> findAll() {
		return userDao.find();
	}

	public User login(User user) {
		User u = userDao.findBy(User.PROP_USERNAME, user.getUsername());
		User newUser = null;
		try {
			newUser = (User) u.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		if(u!=null){
			if(u.getPassword().equals(user.getPassword())){
				newUser.setId(u.getId());
				newUser.setPassword("~!@#");
				return newUser;
			}
		}
		return null;
	}
}
