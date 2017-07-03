package jp.co.axiz.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping(path = "/index")
	public String index() {

		return "index";
	}

	@RequestMapping(path = "/menu")
	public String menu() {

		return "menu";
	}
}
