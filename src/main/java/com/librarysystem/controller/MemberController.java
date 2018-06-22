package com.librarysystem.controller;

import java.time.LocalDate;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.librarysystem.entity.Member;
import com.librarysystem.service.LoanedBookService;
import com.librarysystem.service.MemberService;

@Controller
public class MemberController {

	@Autowired
	private MemberService memberService;

	@Autowired
	private LoanedBookService loanedBookService;

	// Member link mapping

	/* FIND MEMBER */ /* FIND MEMBER */ /* FIND MEMBER */

	@GetMapping("/member-find")
	public String memberFindPage() {
		
		
		return "member/member-find";
	}

	@GetMapping("/find-member-by-card")
	public String findMemberByCardId(@RequestParam("id") int id, Model model) {

		Member member = memberService.findMemberByCardId(id);

		if (member == null) {
			return "redirect:/member-find";
		}

		model.addAttribute("member", member);

		return "member/member";
	}

	/* REGISTER MEMBER */ /* REGISTER MEMBER */ /* REGISTER MEMBER */

	@GetMapping("/member-register")
	public String memberRegisterPage() {
		return "member/member-register";
	}

	@PostMapping("/register-member")
	public String registerMember(@Valid @ModelAttribute("newMember") Member newMember, BindingResult result) {

		if (result.hasErrors()) {
			
			return "redirect:/member-register";
		}
		
		newMember.setDateRegistered(LocalDate.now());
		memberService.registerOrRenewMember(newMember);

		return "redirect:/find-member-by-card?id=" + newMember.getCardId();
			
	}

	/* RENEW MEMBER */ /* RENEW MEMBER */ /* RENEW MEMBER */
	@GetMapping("/member-renew")
	public String memberRenewPage() {
		
		return "member/member-renew";
	}

	/**
	 * Renews membership by updating date registered.
	 **/
	@PostMapping("/renew-membership")
	public String renewMembershipByCardId(@RequestParam("cardId") int cardId) {

		Member member = memberService.findMemberByCardId(cardId);

		if (member == null) {
			

		} else {
			member.setDateRegistered(LocalDate.now());
			memberService.registerOrRenewMember(member);
			
		}

		return "redirect:/member-renew";

	}

	/* RETURN BOOKS */ /* RETURN BOOKS */ /* RETURN BOOKS */
	@PostMapping("/returnBooks")
	public String returnBooksByCardId(int cardId) {

		Member member = memberService.findMemberByCardId(cardId);

		if (member != null)
			loanedBookService.returnBooks(member);

		return "redirect:/find-member-by-card?id=" + cardId;
	}

}
