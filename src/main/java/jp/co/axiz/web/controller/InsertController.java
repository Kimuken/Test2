package jp.co.axiz.web.controller;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.axiz.web.entity.User;
import jp.co.axiz.web.service.UserService;

@Controller
public class InsertController {

	@Autowired
	UserService userService;

	@RequestMapping(path = "/insert", method = { GET })
	public String insert() {

		return "insert";
	}

	@RequestMapping(path = "/insert", method = { POST })
	public String insert(HttpServletRequest request, HttpServletResponse response) {
		// ログインID、パスワードを取得
		String name = request.getParameter("name");
		String tel = request.getParameter("tel");
		String pass = request.getParameter("pass");

		// 入力値のチェック
		if (name == null || pass == null || tel == null || "".equals(name) || "".equals(pass) || "".equals(tel)) {
			// メッセージ設定
			request.setAttribute("msg", "必須項目を入力してください");

			// 次画面指定
			return "insert";
		}

		HttpSession session = request.getSession();

		// 次画面へ渡すデータの設定
		request.setAttribute("name", name);
		request.setAttribute("tel", tel);
		session.setAttribute("pass", pass);
		session.removeAttribute("updateUser");
		session.removeAttribute("insertUserId");
		// 次画面指定
		return "insertConfirm";
	}

	@RequestMapping(path = "/insertConfirm", method = { POST })
	public String insertConfirm(HttpServletRequest request, HttpServletResponse reponse) {

		// ログインID、パスワードを取得
		String name = request.getParameter("name");
		String tel = request.getParameter("tel");
		String rePass = request.getParameter("rePass");

		HttpSession session = request.getSession();

		String pass = (String) session.getAttribute("pass");

		// 入力値のチェック
		if (!pass.equals(rePass)) {
			// メッセージ設定
			request.setAttribute("msg", "前画面で入力したパスワードと一致しません");

			request.setAttribute("name", name);
			request.setAttribute("tel", tel);

			// 次画面指定
			return "insertConfirm";
		}

		User insertUser = new User(name,tel,pass);

		int insertUserId = userService.insert(insertUser);
		if (insertUserId == -1) {
			request.setAttribute("msg", "データベースでエラーが発生しました");

			request.setAttribute("name", name);
			request.setAttribute("tel", tel);
			return "insertConfirm";
		}

		session.setAttribute("insertUserId", insertUserId);
		// 次画面指定
		return "insertResult";

	}

}
