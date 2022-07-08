package com.greatlearning.students.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.greatlearning.students.entity.Student;


@Service
public interface StudentService {
	public List<Student> findAll();
	
	public Student findById(int id);
	
	public void save(Student theStudent);
	
	public void deleteById(int id);

}
