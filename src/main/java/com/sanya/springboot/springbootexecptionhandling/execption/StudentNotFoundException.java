/**
 * 
 */
package com.sanya.springboot.springbootexecptionhandling.execption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Sanya_s
 *
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class StudentNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 2412284371436791059L;

	public StudentNotFoundException(String message){
		super(message);
	}
}
