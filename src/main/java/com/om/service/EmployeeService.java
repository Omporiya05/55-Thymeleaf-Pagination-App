package com.om.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.om.entity.Employee;

public interface EmployeeService {
	
	public Page<Employee> fetchAllEmployees(int pageNumber);
}
