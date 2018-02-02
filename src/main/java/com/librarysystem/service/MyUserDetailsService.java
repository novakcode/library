package com.librarysystem.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.librarysystem.entity.Account;
import com.librarysystem.repository.AccountRepository;

@Service
public class MyUserDetailsService  implements UserDetailsService{
		
	@Autowired
	private AccountRepository accountRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = accountRepository.findByUsername(username);
		
		if(account == null)
		{
			throw new UsernameNotFoundException("User with username "+username+" not found.");
		}
		
		List<GrantedAuthority> authorities = new  ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		
		return new User(account.getUsername(),account.getPassword(),true,true,true,true,authorities);
	}

	
}
