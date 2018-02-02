package com.librarysystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.librarysystem.entity.Account;

@Repository("accountRepository")
public interface AccountRepository extends JpaRepository<Account,String>{

	Account findByUsername(String username);
		
}
