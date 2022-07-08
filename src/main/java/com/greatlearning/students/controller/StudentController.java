package com.greatlearning.students.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.greatlearning.students.entity.Student;
import com.greatlearning.students.service.StudentService;


@Controller
@RequestMapping("/students")
public class StudentController {

	@Autowired
	private StudentService studentService;

	// Add mapping for "/list"
	@RequestMapping("/list")
	public String studentList(Model theModel) {

		// Get Students from DB
		List<Student> theStudents = studentService.findAll();

		// Add to the spring model
		theModel.addAttribute("Students", theStudents);

		return "student-list";
	}

	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {

		// Create model attribute to bind the form data
		Student theStudent = new Student();

		theModel.addAttribute("Student", theStudent);

		return "student-form";
	}

	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("studentId") int id, Model theModel) {

		// get the Student from the service
		Student theStudent = studentService.findById(id);

		// set Student as a model attribute to pre-populate the form
		theModel.addAttribute("Student", theStudent);

		// send over to our form
		return "student-form";
	}

	@PostMapping("/save")
	public String saveStudent(@RequestParam("id") int id,
			@RequestParam("name") String name,
			@RequestParam("department") String department,
			@RequestParam("country") String country) {

		System.out.println(id);
		Student theStudent;
		if(id != 0)
		{
			theStudent = studentService.findById(id);
			theStudent.setName(name);
			theStudent.setDepartment(department);
			theStudent.setCountry(country);
		}
		else
			theStudent = new Student(name, department, country);
		// save the Book
		studentService.save(theStudent);


		// use a redirect to prevent duplicate submissions
		return "redirect:/students/list";

	}

	@RequestMapping("/delete")
	public String delete(@RequestParam("studentId") int id) {

		// delete the Student
		studentService.deleteById(id);

		// redirect to /students/list
		return "redirect:/students/list";

	}

}
