package com.librarysystem.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.librarysystem.entity.Member;
import com.librarysystem.repository.MemberRepository;

@Service("memberService")
public class MemberServiceImpl implements MemberService	{

	private static final Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);
	
	@Autowired
	private MemberRepository memberRepository;
	
	
	@Override
	public Member findMemberByCardId(int cardId) {
		logger.debug("Finding member by card Id:{}",cardId);
		return memberRepository.findMemberByCardId(cardId);
	}
	

	@Override
	public Member registerOrRenewMember(Member member) {
			logger.debug("Registering or renewing member:{}",member);
			return memberRepository.save(member);
	}


	

	

	
	
	
}
