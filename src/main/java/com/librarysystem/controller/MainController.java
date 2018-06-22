package com.librarysystem.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.librarysystem.entity.Book;
import com.librarysystem.service.BookService;
import com.librarysystem.service.LoanedBookService;
import com.librarysystem.service.MemberService;

@Controller
public class MainController {


	/* LIBRARY HOME AND LOGIN PAGE MAPPING */
	@GetMapping({ "/", "/library-home" })
	public String libaryHomePage() {

		return "library/library-home";
	}

	@GetMapping("/library-login")
	public String libraryLoginPage() {

		return "library/library-login";
	}

	

}
