package com.librarysystem.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.librarysystem.SpringBootWebApplication;
import com.librarysystem.entity.LoanedBook;
import com.librarysystem.entity.Member;
import com.librarysystem.service.LoanedBookService;
import com.librarysystem.service.MemberService;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {SpringBootWebApplication.class})
@WebAppConfiguration
public class MemberControllerTests {

	private static final Member member = new Member(1,"First Second","Adress","0600000");
	
	private MockMvc mvc;
	
	@Autowired
	private WebApplicationContext wac;
	
	@MockBean
	private LoanedBookService loanedBookService;
	
	@MockBean
	private MemberService memberService;
	
	@Before
	public void setUp(){
		mvc = MockMvcBuilders.webAppContextSetup(wac).build();
		member.setLoanedBooks(new ArrayList<LoanedBook>());
		when(memberService.findMemberByCardId(member.getCardId())).thenReturn(member);

	}

	
	@Test
	public void findMemberByValidCardId() throws Exception{
		mvc.perform(get("/find-member-by-card").param("id", member.getCardId()+""))
		.andExpect(status().isOk())
		.andExpect(view().name("member/member"))
		.andExpect(model().attribute("member", member));
	}
	
	@Test
	public void findMemberByInvalidCardId() throws Exception{
		mvc.perform(get("/find-member-by-card").param("id", "2"))
		.andExpect(status().isFound())
		.andExpect(view().name("redirect:/member-find"));
	}
	
	@Test
	public void registerValidMember() throws Exception{
		mvc.perform(post("/register-member").param("cardId", member.getCardId()+"")
				.param("fullName", member.getFullName()).param("address", member.getAddress())
				.param("phone", member.getPhone()))
				.andExpect(status().isFound())
				.andExpect(view().name("redirect:/find-member-by-card?id="+member.getCardId()));
		
		verify(memberService,times(1)).registerOrRenewMember(any(Member.class));
		verifyNoMoreInteractions(memberService);
	}
	
	@Test
	public void registerInvalidMember() throws Exception{
		mvc.perform(post("/register-member").param("phone", "a"))
		.andExpect(status().isFound())
		.andExpect(view().name("redirect:/member-register"));
		

		verify(memberService,times(0)).registerOrRenewMember(any(Member.class));
	}
	
	@Test
	public void renewMembershipWithValidCardId() throws Exception{
		mvc.perform(post("/renew-membership").param("cardId", member.getCardId()+""))
		.andExpect(status().isFound())
		.andExpect(view().name("redirect:/member-renew"));

		verify(memberService,times(1)).findMemberByCardId(member.getCardId());
		verify(memberService,times(1)).registerOrRenewMember(any(Member.class));
		verifyNoMoreInteractions(memberService);
		
	}
	
	@Test
	public void renewMembershipWithInvalidCardId() throws Exception{
		mvc.perform(post("/renew-membership").param("cardId", "2"))
		.andExpect(status().isFound());
		
		verify(memberService,times(0)).registerOrRenewMember(any(Member.class));
	}
	
	@Test
	public void returnBooksByValidCardId() throws Exception{
		mvc.perform(post("/returnBooks").param("cardId", member.getCardId()+""))
		.andExpect(status().isFound())
		.andExpect(view().name("redirect:/find-member-by-card?id="+member.getCardId()));
		
		verify(memberService,times(1)).findMemberByCardId(member.getCardId());
		verify(loanedBookService,times(1)).returnBooks(any(Member.class));
		verifyNoMoreInteractions(memberService,loanedBookService);
	}
	
	@Test
	public void returnBooksByInvalidCardId() throws Exception{
		mvc.perform(post("/returnBooks").param("cardId","2"))
		.andExpect(status().isFound());
		
		verify(loanedBookService,times(0)).returnBooks(any(Member.class));
	}
}
