package com.gyanblog.gyanmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.gyanblog.gyanmongo.model.Employee;

public interface EmployeeRepository extends MongoRepository<Employee, String>{

}
