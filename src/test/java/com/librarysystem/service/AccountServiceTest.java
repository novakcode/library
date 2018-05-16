package com.librarysystem.service;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.*;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
	

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceTest {
		
		@Autowired
		private WebApplicationContext context;
		
		private MockMvc mvc;
		
		@Autowired
		private MyUserDetailsService myUserDetailsService;
		
		
		@Before
		public void setup()
		{
			mvc = MockMvcBuilders.webAppContextSetup(context)
					.apply(springSecurity()).build();
			
						
		}
		
		
		
		@Test
		public void requiresAuthentication() throws Exception	
		{
			mvc.perform(get("/library-home")).andExpect(status().is3xxRedirection());
		}
		
		@Test
		public void authenticationSuccess() throws Exception
		{
			mvc.perform(formLogin()).andExpect(authenticated().withUsername("novak"));
		}
		
		
	
		
}
