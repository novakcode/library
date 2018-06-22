package com.librarysystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.librarysystem.entity.Member;
import com.librarysystem.repository.MemberRepository;

@Service("memberService")
public class MemberServiceImpl implements MemberService	{

	@Autowired
	private MemberRepository memberRepository;
	
	
	@Override
	public Member findMemberByCardId(int cardId) {
		return memberRepository.findMemberByCardId(cardId);
	}
	

	@Override
	public void registerOrRenewMember(Member member) {
			memberRepository.save(member);
	}


	

	

	
	
	
}
