package com.librarysystem.service;

import java.time.LocalDate;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.librarysystem.entity.Member;
import com.librarysystem.repository.MemberRepository;

@RunWith(SpringRunner.class)
public class MemberServiceImplTests {
	

	private static final int CARD_ID = 1;
	private static final String FULL_NAME = "Novak Saljic";
	private static final String ADDRESS = "Adresa";
	private static final String PHONE = "0600412";
	private static final LocalDate DATE_REGISTERED = LocalDate.now();

	@TestConfiguration
	static class MemberServiceImplTestContextConfiguration{
		
		@Bean
		public MemberService memberService(){
			return new MemberServiceImpl();
		}
	}
	
	@Autowired
	private MemberService memberService;
	
	@MockBean
	public MemberRepository memberRepository;
	
	
	@Before
	public void setUp(){
		
		Member mockMember = new Member(CARD_ID,FULL_NAME,ADDRESS,PHONE,DATE_REGISTERED);
		Member updatedMember  = new Member(CARD_ID,FULL_NAME,ADDRESS,PHONE,LocalDate.now().plusMonths(1));
		
		when(memberRepository.findMemberByCardId(CARD_ID)).thenReturn(mockMember);
		when(memberRepository.save(any(Member.class))).thenReturn(mockMember);
		when(memberRepository.save(any(Member.class))).thenReturn(updatedMember);
		
		
	}
	
	@Test
	public void whenFindByCardIdReturnMember(){
		Member member = new Member(CARD_ID,FULL_NAME,ADDRESS,PHONE,DATE_REGISTERED);
		
		Member foundMember = memberService.findMemberByCardId(CARD_ID);
		
		verify(memberRepository,times(1)).findMemberByCardId(CARD_ID);
		verifyNoMoreInteractions(memberRepository);
		
		assertThat(member.getCardId(),equalTo(foundMember.getCardId()));
	}
	
	@Test
	public void registeringMemberShouldReturnNewMember(){
		Member member = new Member(CARD_ID,FULL_NAME,ADDRESS,PHONE,DATE_REGISTERED);
		
		Member createdMember = memberService.registerOrRenewMember(member);
		
		verify(memberRepository,times(1)).save(member);
		verifyNoMoreInteractions(memberRepository);
		
		assertThat(member.getCardId(),equalTo(createdMember.getCardId()));
	}
	
	@Test
	public void renewMemberShouldReturnRenewedMember(){
		Member updatedMember = new Member(CARD_ID,FULL_NAME,ADDRESS,PHONE,DATE_REGISTERED.plusMonths(1));
		
		Member createdMember = memberService.registerOrRenewMember(updatedMember);
		
		verify(memberRepository,times(1)).save(updatedMember);
		verifyNoMoreInteractions(memberRepository);
		
		assertThat(updatedMember.getDateRegistered(),equalTo(createdMember.getDateRegistered()));
		assertEquals(updatedMember.getCardId(),updatedMember.getCardId());
	}
	
	
	
	
}
