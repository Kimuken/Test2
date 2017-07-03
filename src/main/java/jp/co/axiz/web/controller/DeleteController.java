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
public class DeleteController {
	@Autowired
	UserService userService;

	@RequestMapping(path = "/delete", method = { GET })
	public String delete() {

		return "delete";
	}

	@RequestMapping(path = "/deleteConfirm", method = { POST })
	public String delete(HttpServletRequest request, HttpServletResponse response) {

		String id_str = request.getParameter("id");
		List<User> searchResult = new ArrayList<User>();

		// 入力値のチェック
		if (id_str == null || "".equals(id_str)) {
			// メッセージ設定
			request.setAttribute("msg", "必須項目を入力してください。");

			// 次画面指定
			return "delete";
		}

		try {
			int id = Integer.parseInt(id_str);
			User searchUser = new User(id);
			searchResult = userService.select(searchUser);
		} catch (Exception e) {

		}

		if (searchResult == null || searchResult.size() == 0) {
			// メッセージ設定
			request.setAttribute("msg", "入力されたデータは存在しません。");

			// 次画面指定
			return "delete";

		}

		HttpSession session = request.getSession();

		// 次画面へ渡すデータの設定
		session.setAttribute("deleteUser", searchResult.get(0));
		session.removeAttribute("updateUser");
		session.removeAttribute("insertUserId");

		// 次画面指定
		return "deleteConfirm";
	}

	@RequestMapping(path = "/delete", method = { POST })
	public String deleteConfirm(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();

		User delUser = (User) session.getAttribute("deleteUser");

		int count = userService.delete(delUser);
		if (count == -1) {
			// メッセージ設定
			request.setAttribute("msg", "データベースでエラーが発生しました。");

			// 次画面指定
			return "deleteConfirm";
		}

		// 次画面へ渡すデータの設定

		// 次画面指定
		return "deleteResult";

	}
}
