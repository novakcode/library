package com.librarysystem.repository;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.librarysystem.entity.Account;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountRepositoryTest {

	
		@Autowired
		private TestEntityManager entityManager;
		
		@Autowired
		private AccountRepository accountRepository;
		
		private PasswordEncoder passwordEncoder()
		{
			return new BCryptPasswordEncoder();
		}
		
		
		@Test
		public void findAccountByUsernameReturnAccount()
		{
			Account mockAccount = new Account();
			String mockUsername =  "alex";
			String mockPassword = passwordEncoder().encode("pass");
			mockAccount.setUsername(mockUsername);
			mockAccount.setPassword(mockPassword);
			
			entityManager.persist(mockAccount);
			entityManager.flush();
			
			Account findAccount = accountRepository.findByUsername("alex");
			
			
			
			
			assertThat(mockAccount.getUsername(),equalTo(findAccount.getUsername()));
		}
		
}
