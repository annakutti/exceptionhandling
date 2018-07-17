/**
 * 
 */
package com.sanya.springboot.springbootexecptionhandling.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sanya.springboot.springbootexecptionhandling.execption.StudentNotFoundException;
import com.sanya.springboot.springbootexecptionhandling.model.Student;
import com.sanya.springboot.springbootexecptionhandling.repository.StudentRepository;

/**
 * @author Sanya_s
 *
 */
@RestController
public class StudentController {
	
	@Autowired
	StudentRepository studentRepository;

	@GetMapping("/students")
	public List<Student> getStudent(){
		return studentRepository.findAll();
	}
	
/*	@GetMapping("/students/{studentId}")
	public Student getStudentById(@PathVariable("studentId") final long studentId){
		Optional<Student> student = studentRepository.findById(studentId);
		if(!student.isPresent()){
			throw new StudentNotFoundException("no records for :"+studentId);
		}
		return student.get();
	}*/
	
	@GetMapping("/students/{studentId}")
	public Resource<Student> getStudentById(@PathVariable("studentId") final long studentId){
		Optional<Student> student = studentRepository.findById(studentId);
		if(!student.isPresent()){
			throw new StudentNotFoundException("no records for :"+studentId);
		}
		Resource<Student> resource = new Resource<Student>(student.get());
		ControllerLinkBuilder linkBuilder = 
				ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).getStudent()); 
		resource.add(linkBuilder.withRel("all-students"));
		return resource;
	}
	
	@PostMapping("/students/add")
	public ResponseEntity<Student> addStudent(@RequestBody final Student student){
		Student newStudent = studentRepository.save(student);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newStudent.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping("/students/{studentId}/remove")
	public String removeStudent(@PathVariable("studentId") final long studentId){		
		studentRepository.deleteById(studentId);
		return "Student with Id : "+studentId+" is deleted";
	}
	
	/*@PutMapping("/students/{studentId}/update")
	public String updateStudent(@PathVariable("studentId") final long studentId ,@RequestBody final Student student){
		Optional<Student> studentDetail = studentRepository.findById(studentId);
		if(!studentDetail.isPresent()){
			throw new StudentNotFoundException("Student with Id :"+studentId+" dosent exisit");
		}
		student.setId(studentId);
		studentRepository.save(student);
		return "Student detail is updated";
	}*/
	
	@PutMapping("/students/{studentId}/update")
	public ResponseEntity<Object> updateStudent(@PathVariable("studentId") final long studentId ,@RequestBody final Student student){
		Optional<Student> studentDetail = studentRepository.findById(studentId);
		if(!studentDetail.isPresent()){
			throw new StudentNotFoundException("Student with Id :"+studentId+" dosent exisit");
			//return ResponseEntity.notFound().build();
		}
		student.setId(studentId);
		studentRepository.save(student);
		return ResponseEntity.noContent().build();
	}
}
