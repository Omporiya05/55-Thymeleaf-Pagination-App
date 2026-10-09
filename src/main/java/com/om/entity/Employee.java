package com.om.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="emp")
@Data
public class Employee {
	@Id
	private Integer empno;
	private String ename;
	private Double salary;
	private Integer deptno;
}
