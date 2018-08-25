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
	

	
	private Member member = new Member(1,"Example Name","Adress","06024");

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
		
		
		Member updatedMember  = member;
		updatedMember.setDateRegistered(LocalDate.now().plusMonths(1));
		
		when(memberRepository.findMemberByCardId(member.getCardId())).thenReturn(member);
		when(memberRepository.save(any(Member.class))).thenReturn(member);
		when(memberRepository.save(any(Member.class))).thenReturn(updatedMember);
		
		
	}
	
	@Test
	public void whenFindByCardIdReturnMember(){

		
		Member foundMember = memberService.findMemberByCardId(member.getCardId());
		
		verify(memberRepository,times(1)).findMemberByCardId(member.getCardId());
		verifyNoMoreInteractions(memberRepository);
		
		assertThat(member.getCardId(),equalTo(foundMember.getCardId()));
	}
	
	@Test
	public void registeringMemberShouldReturnNewMember(){
		
		Member createdMember = memberService.registerOrRenewMember(member);
		
		verify(memberRepository,times(1)).save(member);
		verifyNoMoreInteractions(memberRepository);
		
		assertThat(member.getCardId(),equalTo(createdMember.getCardId()));
	}
	
	@Test
	public void renewMemberShouldReturnRenewedMember(){
		Member updatedMember = member;
		updatedMember.setDateRegistered(LocalDate.now().plusMonths(1));
		
		Member createdMember = memberService.registerOrRenewMember(updatedMember);
		
		verify(memberRepository,times(1)).save(updatedMember);
		verifyNoMoreInteractions(memberRepository);
		
		assertThat(updatedMember.getDateRegistered(),equalTo(createdMember.getDateRegistered()));
		assertEquals(updatedMember.getCardId(),updatedMember.getCardId());
	}
	
	
	
	
}
