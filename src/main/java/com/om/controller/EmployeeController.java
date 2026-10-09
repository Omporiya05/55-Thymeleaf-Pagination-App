package com.om.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.om.entity.Employee;
import com.om.service.EmployeeService;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/view")
	public String show(@RequestParam(name="pageNumber",defaultValue="0") int pageNumber,
			Model model) {
		
		// If pageNumber is less than 1, use 0.
		// Otherwise, subtract 1 from pageNumber.
		// pn=0,zbp=0 pn=1,zbp=0 pn =2,zbp=1 ...
		
		int zeroBasedPage = (pageNumber < 1) ? 0 : pageNumber - 1;
		Page<Employee> pageEmployee = employeeService.fetchAllEmployees(zeroBasedPage);
		int totalPages = pageEmployee.getTotalPages();
		int currentPage = pageEmployee.getNumber()+1;
		int windowSize = 5;
		
		// windowSize / 2 is 5 / 2 = 2 because integer division is used.
		// currentPage - 2 : calculates a starting point around the current page.
		// Math.max(1, ...) : ensures that startPage never goes below 1.
		// Example : cp = 4 ,startPage = Math.max(1,4-2) startPage = Math.max(1,2) = 2
		int startPage = Math.max(1, currentPage-(windowSize/2));
		
		
		// startPage + windowSize - 1 calculates the end of the five-page range.
		// Math.min(totalPages, ...) ensures that endPage never exceeds the total number of pages.
		int endPage   = Math.min(totalPages, startPage+windowSize-1);
		
		
		if (endPage - startPage + 1 < windowSize) {
			startPage = Math.max(1, endPage - windowSize + 1);
		}
		
		model.addAttribute("empList", pageEmployee.getContent());
		model.addAttribute("pageNumber", currentPage);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("hasNext", pageEmployee.hasNext());
		model.addAttribute("hasPrevious", pageEmployee.hasPrevious());
		return "View";
	}
}

// We divide windowSize by 2 to keep the current page near the middle of the page buttons.