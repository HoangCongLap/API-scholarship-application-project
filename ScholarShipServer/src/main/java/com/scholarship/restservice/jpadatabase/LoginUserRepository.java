package com.scholarship.restservice.jpadatabase;


import com.scholarship.restservice.entites.LoginUser;
import org.springframework.data.repository.CrudRepository;


public interface LoginUserRepository extends CrudRepository<LoginUser, Integer> {
    LoginUser findByUsename(String username);
}