package com.vietanh.employeeserice.command.event;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.vietanh.employeeserice.command.data.Employee;
import com.vietanh.employeeserice.command.data.EmployeeReponsitory;

@Component
public class EmployeeEventsHandler {
    @Autowired EmployeeReponsitory  employeeReponsitory;

    @EventHandler
    public void on(EmployeeCreateEvent event) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(event, employee);
        employeeReponsitory.save(employee);
    }
    
    @EventHandler
    public void on(EmployeeUpdateEvent event){
        Employee employee = employeeReponsitory.getById(event.getEmployeeId());
        BeanUtils.copyProperties(event, employee);
        employeeReponsitory.save(employee);
    }

    @EventHandler
    public void on(EmployeeDeleteEvent event){
        employeeReponsitory.deleteById(event.getEmployeeId());
    }
}
