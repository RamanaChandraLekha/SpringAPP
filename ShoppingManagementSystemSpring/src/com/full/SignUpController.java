package com.full;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

@Controller

public class SignUpController {
	HelperClass helperClass = new HelperClass();

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public String display() {
		return "display";
	}

	@RequestMapping(value = "/signIn", method = RequestMethod.GET)
	public String signIn() {

		return "SignUp";

	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST, consumes = { "application/json" })
	public ModelAndView submit(@RequestBody User users)

	{

		boolean mailIdFound = helperClass.userRegistration(users);
		ModelAndView model = new ModelAndView("view");
		if (!mailIdFound) {
			model.addObject("msg", "Registerd sucessfully");
			return model;
		} else {
			model.addObject("msg", "account is already created with this mail");
			return model;
		}

	}

	@RequestMapping(value = "/loginPage", method = RequestMethod.GET)
	public String loginDetails() {

		return "Login";

	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(@RequestParam("mail") String email, @RequestParam("password") String pwd,
			HttpServletRequest req, HttpSession session) {

		boolean flag = helperClass.verifyinDetails(email, pwd);
		if (flag) {
			session.setAttribute("email", email);

			return new ModelAndView("index");
		}

		else
			return new ModelAndView("view", "msg", "invalid credentials,please login again");

	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate();
		return "display";

	}

}
