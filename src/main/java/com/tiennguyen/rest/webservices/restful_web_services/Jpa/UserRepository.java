package com.tiennguyen.rest.webservices.restful_web_services.Jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tiennguyen.rest.webservices.restful_web_services.user.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
