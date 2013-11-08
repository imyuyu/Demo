package org.demo.plugin.exchange.interfaces.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 * 交换中心页面控制器，主要用于各种服务器端页面
 * @author Developer huzy
 *
 */
public class ExchangeController extends MultiActionController{
	
	public ModelAndView index(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mav = new ModelAndView("exchange/login");
		return mav;
	}
}
