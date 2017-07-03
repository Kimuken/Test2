package jp.co.axiz.web.controller;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.axiz.web.entity.User;
import jp.co.axiz.web.service.UserService;

@Controller
public class SelectController {
	@Autowired
	UserService userService;

	@RequestMapping(path = "/select", method = { GET })
	public String select() {

		return "select";
	}

	@RequestMapping(path = "/list", method = { GET })
	public String list(HttpServletRequest request, HttpServletResponse response) {
		String idStr = request.getParameter("id");
		Integer id = null;
		String name = request.getParameter("name");
		String telephone = request.getParameter("tel");

		if(!idStr.equals("")){
		try {
			id = Integer.parseInt(idStr);
		} catch (Exception e) {
			request.setAttribute("msg", "入力されたデータはありませんでした。");
			return "select";
		}}else{
			id = null;
		}

		List<User> selectResult = new ArrayList<User>();

		User selectUser = new User(id,name,telephone);
		selectResult = userService.select(selectUser);

		if (selectResult == null || selectResult.size() == 0) {
			request.setAttribute("msg", "入力されたデータはありませんでした。");
			return "select";
		}

		HttpSession session = request.getSession();
		session.removeAttribute("updateUser");
		session.removeAttribute("insertUserId");

		request.setAttribute("findData", selectResult);
		return "selectResult";
	}

}
