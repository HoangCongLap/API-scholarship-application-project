package com.scholarship.restservice.jpadatabase;

import com.scholarship.restservice.entites.Scholarship;
import com.scholarship.restservice.entites.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepsitory extends CrudRepository<Student, String> {
}
