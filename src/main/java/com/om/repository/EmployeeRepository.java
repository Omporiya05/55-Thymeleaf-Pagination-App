package com.om.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.om.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
