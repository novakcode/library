package com.librarysystem.service;

import com.librarysystem.entity.Member;

	
public interface MemberService {
		
		
		Member findMemberByCardId(int cardId);
		Member registerOrRenewMember(Member member);

		
		
		
		
}
