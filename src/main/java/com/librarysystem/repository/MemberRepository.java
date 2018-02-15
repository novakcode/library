package com.librarysystem.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.librarysystem.entity.Member;

@Repository("memberRepository")
public interface MemberRepository extends JpaRepository<Member, Integer>, CrudRepository<Member,Integer>{
		
	
		Member findMemberByCardId(int cardId);	
		Page<Member> findMemberByFullNameContaining(String fullName,Pageable pageable);
		
		
}
	