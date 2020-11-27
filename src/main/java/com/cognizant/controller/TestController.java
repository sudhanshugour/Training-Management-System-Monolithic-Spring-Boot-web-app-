package com.cognizant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping(path = "/test")
@SessionAttributes("userId")
public class TestController {

	
	@GetMapping("testSession")
	public  String testSessionn(Model model) {
		model.addAttribute("userId",121);
		System.out.println(model.getAttribute("userId"));
		return "testSession";
	} 
	
	
}
