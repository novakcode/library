package com.librarysystem;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.librarysystem.SpringBootWebApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootWebApplication.class)
public class SpringContextIntegrationTest {
 
	@Test
	public void whenContextIsBootstrapped_thenNoException(){
		
	}

}
