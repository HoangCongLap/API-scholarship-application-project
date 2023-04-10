package com.scholarship.restservice.jpadatabase;


import com.scholarship.restservice.entites.LoginUser;
import com.scholarship.restservice.entites.Scholarship;
import com.scholarship.restservice.entites.Student;
import org.springframework.data.repository.CrudRepository;


public interface ScholarshipRepository extends CrudRepository<Scholarship, String> {
}