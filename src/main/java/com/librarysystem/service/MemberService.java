package com.librarysystem.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.librarysystem.entity.Member;

	
public interface MemberService {
		
		
		Member findMemberByCardId(int cardId);
		Page<Member> findMemberByFullName(String name,Pageable pageable);
		void registerOrRenewMember(Member member);

		
		
		
		
}
