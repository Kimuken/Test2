package jp.co.axiz.web.controller;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.axiz.web.entity.Admin;
import jp.co.axiz.web.service.UserService;

@Controller
public class AuthController {
	@Autowired
	UserService userService;

	@RequestMapping(path = "/login", method = { GET })
	public String login() {

		return "login";
	}

	@RequestMapping(path = "/login", method = { POST })
	public String login(@RequestParam("id") String id, @RequestParam("pass") String pass, HttpSession session,
			Model model) {

		Admin admin = userService.findByIdAndPass(id, pass);
		
		if (admin != null) {
			session.setAttribute("loginAdmin", admin);
			return "menu";
		} else {
			model.addAttribute("msg", "IDまたはPASSが間違っています");
			return "login";
		}
	}

	@RequestMapping(path = "/logout", method = { POST })
	public String logout(HttpServletRequest request) {

		HttpSession session = request.getSession();
		session.removeAttribute("loginAdmin");
		session.removeAttribute("insertUserId");
		session.removeAttribute("deleteUser");
		session.removeAttribute("pass");

		return "logout";
	}

}
