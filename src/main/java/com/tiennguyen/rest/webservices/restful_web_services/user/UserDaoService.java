package com.tiennguyen.rest.webservices.restful_web_services.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	private static Integer userCount=1;
	private static List<User> users=new ArrayList<>();
	static {
		users.add(new User(userCount++,"Tien",LocalDate.now().minusYears(23)));
		users.add(new User(userCount++,"Adam",LocalDate.now().minusYears(32)));
		users.add(new User(userCount++,"Jin",LocalDate.now().minusYears(12)));

	}
	public List<User>findAll(){
		return users;
	}
	public User findOne(int id) {
		Predicate<? super User> predicate = user -> user.getId().equals(id); 
		return users.stream().filter(predicate).findFirst().orElse(null);
	} 
	public User save(User u) {
		u.setId(userCount++);
		users.add(u);
		return u;
	}
	public void deleteById(int id) {
		Predicate<? super User> predicate = user -> user.getId().equals(id); 
		users.removeIf(predicate);
		
	}

}
