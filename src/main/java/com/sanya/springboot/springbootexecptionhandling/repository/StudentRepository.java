/**
 * 
 */
package com.sanya.springboot.springbootexecptionhandling.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sanya.springboot.springbootexecptionhandling.model.Student;

/**
 * @author Sanya_s
 *
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
