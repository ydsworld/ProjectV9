package com.ydsworld.controller;


import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ydsworld.model.Form;

@Controller
@RequestMapping("/form")
public class FormController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String initForm(Model model) {
		Form form = new Form();
		model.addAttribute("form", form);
		return "form";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String submitForm(@Valid Form form, BindingResult result) {
		String returnVal = "successForm";
		if(result.hasErrors()) {
			returnVal = "form";
		}
		return returnVal;
	}

}