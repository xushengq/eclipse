package com.cy.pj.sys.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class PageController {
	
	
	@RequestMapping("doLoginUI")
	public String doLoginUI(){
			return "login";
	}

	
	@RequestMapping("doIndexUI")
	public String doIndexUI() {
		return "starter";
	}
	
	@RequestMapping("doLogout")
	public String doIndexUI1() {
		return "login";
	}
	
	/*
	 * @RequestMapping("log/log_list") public String doLogUI() { return
	 * "sys/log_list"; }
	 * 
	 * @RequestMapping("menu/menu_list") public String doMenuUI() { return
	 * "sys/menu_list"; }
	 */

	@RequestMapping("doPageUI")
	public String doPageUI() {
		return "common/page";
	}
	@RequestMapping("{module}/{moduleUI}")
	public String doModuleUI(@PathVariable String moduleUI) {
			return "sys/"+moduleUI;
	}



	
}
