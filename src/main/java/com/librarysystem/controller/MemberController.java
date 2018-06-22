package com.librarysystem.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.librarysystem.entity.Member;
import com.librarysystem.service.MemberService;

@Controller
public class MemberController {

	
	@Autowired
	private MemberService memberService;
	
	private static final int PAGE_SIZE = 5;
	private static final int NAVIGATION_PAGE_SIZE = 5;
	
	
	// Member link mapping

	/* FIND MEMBER */ /* FIND MEMBER */ /* FIND MEMBER */

	@GetMapping("/member-find")
	public String memberFindPage() {
		return "member/member-find";
	}
	
	
	
	/* REGISTER MEMBER */ /* REGISTER MEMBER */ /* REGISTER MEMBER */
	
	
	@GetMapping("/member-register")
	public String memberRegisterPage() {
		return "member/member-register";
	}
	
	@PostMapping("/register-member")
	public String registerMember(@Valid @ModelAttribute("newMember") Member newMember,BindingResult result){
		
		
		return "redirect:/member-register";
		
	}
	
	

	/* RENEW MEMBER */ /* RENEW MEMBER */ /* RENEW MEMBER */
	@GetMapping("/member-renew")
	public String memberRenewPage() {
		return "member/member-renew";
	}

	
	
}
