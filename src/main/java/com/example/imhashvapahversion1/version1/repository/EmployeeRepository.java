package com.example.imhashvapahversion1.version1.repository;

import com.example.imhashvapahversion1.version1.Entity.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee,Long> {

}
