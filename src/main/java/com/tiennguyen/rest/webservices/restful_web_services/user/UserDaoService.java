package com.tiennguyen.rest.webservices.restful_web_services.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	private static List<User> users=new ArrayList<>();
	static {
		users.add(new User(1,"Tien",LocalDate.now().minusYears(23)));
		users.add(new User(2,"Adam",LocalDate.now().minusYears(32)));
		users.add(new User(3,"Jin",LocalDate.now().minusYears(12)));

	}
	public List<User>findAll(){
		return users;
	}
	public User findOne(int id) {
		Predicate<? super User> predicate = user -> user.getId().equals(id); 
		return users.stream().filter(predicate).findFirst().get();
	} 

}
