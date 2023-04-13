package com.jpa.test;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.jpa.test.dao.UserRepository;
import com.jpa.test.entities.User;

@SpringBootApplication
public class BootJpaExampleApplication {

	public static void main(String[] args) {
	 ApplicationContext contex=	SpringApplication.run(BootJpaExampleApplication.class, args);
	UserRepository userRepository= contex.getBean(UserRepository.class);
	
	User user=new User();
//	user.setId(122);
	user.setName("test");
	user.setCity("badlapur");
	
	User user1=new User();
//	user.setId(122);
	user1.setName("test");
	user1.setCity("badlapur");
	
	List<User> list=List.of(user,user1);
	Iterable<User> result=userRepository.saveAll(list);
	
	result.forEach(users->{
		System.out.println(user);
	});
	
	}

}
