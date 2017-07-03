package jp.co.axiz.web.controller;

import static org.springframework.web.bind.annotation.RequestMethod.*;

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
public class UpdateController {
	@Autowired
	UserService userService;

	@RequestMapping(path = "/update", method = { GET })
	public String update() {
		return "update";
	}

	@RequestMapping(path = "/updateInput", method = { POST })
	public String updateInput(HttpServletRequest request) {
		HttpSession session;
		session = request.getSession();



		String id_str = request.getParameter("id");
		int id = 0;
		try {
			id = Integer.parseInt(id_str);
		} catch (Exception e) {
			request.setAttribute("msg", "必須項目を入力してください");
			return "update";
		}
		User user = new User(id);
		List<User> userList = userService.select(user);
		if (userList.isEmpty()) {
			request.setAttribute("msg", "入力されたデータは存在しません");
			return "update";
		}
		session.setAttribute("updateUser", userList.get(0));

		return "updateInput";
	}

	@RequestMapping(path = "/updateConfirm", method = { POST })
	public String updateInput(HttpServletRequest request, HttpServletResponse response) {

		String newName = request.getParameter("newName");
		String newTel = request.getParameter("newTel");
		String newPass = request.getParameter("newPass");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("updateUser");
		String oldPass = user.getPassword();

		// 入力値のチェック
		if (newName == null || newPass == null || newTel == null || "".equals(newName) || "".equals(newPass)
				|| "".equals(newTel)) {
			// メッセージ設定
			request.setAttribute("msg", "入力されていない項目があります。");

			return "updateInput";
		}
		if (newName.equals(user.getUserName()) && newTel.equals(user.getTelephone())
				&& newPass.equals(user.getPassword())) {
			// メッセージ設定
			request.setAttribute("msg", "１項目以上変更してください。");

			return "updateInput";
		}

		// 次画面へ渡すデータの設定
		session.setAttribute("newName", newName);
		if(user.getPassword().equals(newPass)){
			newPass = oldPass;
		}else{
			oldPass = "";
		}
		session.setAttribute("newPass", newPass);
		session.setAttribute("oldPass", oldPass);
		session.setAttribute("newTel", newTel);

		return "updateConfirm";
	}

	@RequestMapping(path = "/update", method = { POST })
	public String updateConfirm(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		String newPass = (String) session.getAttribute("newPass");
		String rePass = request.getParameter("rePass");
		String newName = (String) session.getAttribute("newName");
		String newTel = (String) session.getAttribute("newTel");
		int id = Integer.parseInt(request.getParameter("id"));
		User user = (User) session.getAttribute("updateUser");

		if (!newPass.equals(rePass)) {
			// メッセージ設定
			request.setAttribute("msg", "前画面で入力したパスワードと一致しません。");

			// 次画面指定
			return "updateConfirm";
		}

		User updateUser = new User(id, newName, newTel, newPass);

		int count = userService.update(updateUser);
		if (count == -1) {
			// メッセージ設定
			request.setAttribute("msg", "データベースでエラーが発生しました。");

			// 次画面指定
			return "updateConfirm";
		}

		// 次画面指定
		return "updateResult";
	}
	@RequestMapping(path = "/updateInput", method = {GET })
	public String updateInputreturn(HttpServletRequest request) {
		return "updateInput";
	}

}
