package com.vietanh.employeeserice.command.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeReponsitory extends JpaRepository<Employee,String>{
    Employee getById(String id);
}
