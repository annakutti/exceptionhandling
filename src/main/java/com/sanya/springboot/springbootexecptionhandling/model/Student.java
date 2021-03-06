/**
 * 
 */
package com.sanya.springboot.springbootexecptionhandling.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Sanya_s
 *
 */
@Entity
public class Student {

	@Id
	@GeneratedValue
	private long id;
	
	private String name;
	
	private int age;

	/**
	 * @param id
	 * @param name
	 * @param age
	 */
	public Student(long id, String name, int age) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
	}

	/**
	 * 
	 */
	public Student() {
		super();
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}
	
	
	
}
