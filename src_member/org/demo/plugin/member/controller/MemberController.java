package org.demo.plugin.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import org.demo.plugin.member.domain.User;
import org.demo.plugin.member.manager.UserManager;

public class MemberController extends MultiActionController{
	private UserManager userManager;

	public ModelAndView index(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mav = new ModelAndView("../index");
		return mav;
	}
	public ModelAndView toIndex(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mav = new ModelAndView("../index");
		return mav;
	}
	public ModelAndView toLogin(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mav = new ModelAndView("main/login");
		return mav;
	}
	
	public ModelAndView toRegister(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mav = new ModelAndView("main/register");
		return mav;
	}
	
	/**
	 * 注册用户
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	public ModelAndView doRegister(HttpServletRequest request,HttpServletResponse response) throws Exception{
		ModelAndView mav = new ModelAndView("main/login");
		User user = new User();
		user.setIdIfNew();
		bind(request, user);
		boolean flag = userManager.save(user);
		if(!flag)//注册失败
			mav.setViewName("main/register");
		return mav;
	}
	
	/**
	 * 用户登录
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	public ModelAndView doLogin(HttpServletRequest request,HttpServletResponse response) throws Exception{
		ModelAndView mav = new ModelAndView("main/index");
		User user = new User();
		bind(request, user);
		user = userManager.login(user);
		if(user == null){
			mav.addObject("msg","登录失败");
			mav.setViewName("main/login");
		}
		return mav;
	}
	
	public ModelAndView doTree(HttpServletRequest request,HttpServletResponse response) throws Exception{
		return null;
	}
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}
}
