package com.gyanblog.gyanmongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gyanblog.gyanmongo.model.Employee;
import com.gyanblog.gyanmongo.repository.EmployeeRepository;

@SpringBootApplication
public class GyanMongoApplication implements CommandLineRunner {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(GyanMongoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Employee emp = new Employee();
		emp.setId("arn-1234");
		
		String data = "{\"name\": \"Gorav Singal\"}";
		
		emp.setData(data);

		this.employeeRepository.save(emp);
		
		Employee result = this.employeeRepository.findById("arn-1234").orElse(null);
		System.out.println("Found employee: " + result.getId());
		
		System.out.println("Done");
	}

}
