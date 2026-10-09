package com.om.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.om.entity.Employee;
import com.om.repository.EmployeeRepository;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public Page<Employee> fetchAllEmployees(int pageNumber) {
		int pageSize = 3;
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		return employeeRepository.findAll(pageable);
	}

}
