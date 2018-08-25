package com.librarysystem.security;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.librarysystem.SpringBootWebApplication;
import com.librarysystem.entity.Account;
import com.librarysystem.repository.AccountRepository;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SpringBootWebApplication.class})
@WebAppConfiguration
public class UserDetailsServiceTests{

	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private AuthenticationProvider authenticationProvider;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	
	
	@Test
	public void withExistingUserAuthenticate(){
		Account account = new Account();
		account.setUsername("user");
		account.setPassword(passwordEncoder.encode("nole99"));
		
		accountRepository.save(account);
		
		UsernamePasswordAuthenticationToken auth = new 	UsernamePasswordAuthenticationToken("user","nole99");
		Authentication authentication = authenticationProvider.authenticate(auth);
	
		Assert.assertEquals(authentication.getName(),account.getUsername());
	}
	
	@Test
	public void withNonExistingUser_throwException(){
	

		thrown.expect(BadCredentialsException.class);
		
		Account account = new Account();
		account.setUsername("user");
		account.setPassword(passwordEncoder.encode("nole99"));
		
		accountRepository.save(account);
		
		UsernamePasswordAuthenticationToken auth = new 	UsernamePasswordAuthenticationToken("admin","admin");
		authenticationProvider.authenticate(auth);
		
		
	}
	
	
}
