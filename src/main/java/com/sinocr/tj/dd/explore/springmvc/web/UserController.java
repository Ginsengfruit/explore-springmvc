package com.sinocr.tj.dd.explore.springmvc.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sinocr.tj.dd.explore.springmvc.domain.User;
import com.sinocr.tj.dd.explore.springmvc.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	UserService userService;

	@RequestMapping("/register")
	public String register() {
		return "user/register";
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView createUser(User user) {
		userService.createUser(user);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/createSuccess");
		mav.addObject("user", user);
		return mav;
	}

	@RequestMapping("/{userId}")
	public ModelAndView showDetail(@PathVariable("userId") String userId) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/showDetail");
		mav.addObject(userService.getUserById(userId));
		return mav;
	}

	@RequestMapping(value = "/handler41")
	public String handle41(@RequestBody String requestBody) {
		logger.info(requestBody);
		return "success";
	}
}
