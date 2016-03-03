package com.sinocr.tj.dd.explore.springmvc.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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

	@ResponseBody
	@RequestMapping(value = "/handle42/{imageId}")
	public byte[] handle42(@PathVariable("imageId") String imageId) throws IOException {
		logger.info("ad image of " + imageId);
		Resource res = new ClassPathResource("/image.jpg");
		byte[] fileDate = FileCopyUtils.copyToByteArray(res.getInputStream());
		return fileDate;
	}

	@RequestMapping(value = "/handle43")
	public String handle43(HttpEntity<String> httpEntity) {
		long contentLen = httpEntity.getHeaders().getContentLength();
		logger.info("contentLend:" + contentLen + " body: " + httpEntity.getBody());
		return "success";
	}

	@RequestMapping(value = "/handle44/{imageId}")
	public ResponseEntity<byte[]> handle44(@PathVariable("imageId") String imageId) throws Throwable {
		Resource res = new ClassPathResource("/image.jpg");
		byte[] fileData = FileCopyUtils.copyToByteArray(res.getInputStream());
		ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(fileData, HttpStatus.OK);
		return responseEntity;
	}

	@RequestMapping(value = "/handle51")
	public ResponseEntity<User> handle51(HttpEntity<User> requestEntity) {
		User user = requestEntity.getBody();
		user.setUserId("1000");
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/handle81")
	public String handle81(@RequestParam("user") User user, ModelMap modelMap) {
		modelMap.put("user", user);
		logger.info("user: " + user.getUserName() + " : " + user.getPassword() + " : " + user.getRealName());
		return "/user/showUser";
	}

	@RequestMapping(value = "/handle91")
	public String handle91(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "/user/rergister3";
		} else {
			return "/user/showUser";
		}
	}

	@RequestMapping(value = "/handle92")
	public String handle92(@ModelAttribute("user") User user, BindingResult bindingResult) {
		ValidationUtils.rejectIfEmptyOrWhitespace(bindingResult, "userName", "required");
		if ("aaaa".equalsIgnoreCase(user.getUserName())) {
			bindingResult.rejectValue("userName", "reserved");
		}
		if (bindingResult.hasErrors()) {
			return "/user/register4";
		} else {
			return "/user/showUser";
		}
	}

	@RequestMapping(value = "/showUserList")
	public String showUserList(ModelMap mm) {
		Calendar calendar = new GregorianCalendar();
		List<User> userList = new ArrayList<>();
		User user1 = new User();
		user1.setUserName("tom");
		user1.setRealName("Tom Hanks");
		calendar.set(1981, 1, 1);
		user1.setBirthday(calendar.getTime());
		User user2 = new User();
		user2.setUserName("john");
		user2.setRealName("John Cena");
		user2.setBirthday(calendar.getTime());
		userList.add(user1);
		userList.add(user2);
		mm.addAttribute("userList", userList);
		return "user/userList";
	}

	@RequestMapping(value = "/showUserListByFtl")
	public String showUserListInFtl(ModelMap mm) {
		logger.info("showUserListInFtl called!");
		Calendar calendar = new GregorianCalendar();
		List<User> userList = new ArrayList<>();
		User user1 = new User();
		user1.setUserName("tom");
		user1.setRealName("Tom Hanks");
		calendar.set(1981, 1, 1);
		user1.setBirthday(calendar.getTime());
		User user2 = new User();
		user2.setUserName("john");
		user2.setRealName("John Cena");
		user2.setBirthday(calendar.getTime());
		userList.add(user1);
		userList.add(user2);
		mm.addAttribute("userList", userList);
		return "userListFtl";
	}

	@RequestMapping(value = "/showUserListByXls")
	public String showUserListInExcel(ModelMap mm) {
		Calendar calendar = new GregorianCalendar();
		List<User> userList = new ArrayList<>();
		User user1 = new User();
		user1.setUserName("tom");
		user1.setRealName("Tom Hanks");
		calendar.set(1981, 1, 1);
		user1.setBirthday(calendar.getTime());
		User user2 = new User();
		user2.setUserName("john");
		user2.setRealName("John Cena");
		user2.setBirthday(calendar.getTime());
		userList.add(user1);
		userList.add(user2);
		mm.addAttribute("userList", userList);
		return "userListExcel";
	}

	@RequestMapping(value = "/showUserListByPdf")
	public String showUserListInPdf(ModelMap mm) {
		Calendar calendar = new GregorianCalendar();
		List<User> userList = new ArrayList<>();
		User user1 = new User();
		user1.setUserName("tom");
		user1.setRealName("Tom Hanks");
		calendar.set(1981, 1, 1);
		user1.setBirthday(calendar.getTime());
		User user2 = new User();
		user2.setUserName("john");
		user2.setRealName("John Cena");
		user2.setBirthday(calendar.getTime());
		userList.add(user1);
		userList.add(user2);
		mm.addAttribute("userList", userList);
		return "userListPdf";
	}

}
