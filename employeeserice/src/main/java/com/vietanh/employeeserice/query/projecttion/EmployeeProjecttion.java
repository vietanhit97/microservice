package com.vietanh.employeeserice.query.projecttion;

import java.util.ArrayList;
import java.util.List;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.vietanh.employeeserice.command.data.Employee;
import com.vietanh.employeeserice.command.data.EmployeeReponsitory;
import com.vietanh.employeeserice.query.model.EmployeeResponseModel;
import com.vietanh.employeeserice.query.queries.GetAllEmployeeQuery;
import com.vietanh.employeeserice.query.queries.GetEmployeeQuery;

@Component
public class EmployeeProjecttion {
    @Autowired EmployeeReponsitory employeeRepository;

    @QueryHandler
    public EmployeeResponseModel handler(GetEmployeeQuery getEmployeeQuery){
        EmployeeResponseModel model = new EmployeeResponseModel();
        Employee employee = employeeRepository.getById(getEmployeeQuery.getEmployeeId());
        BeanUtils.copyProperties(employee, model);
        return model;
    }

    @QueryHandler
    public List<EmployeeResponseModel> handler(GetAllEmployeeQuery query){
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeResponseModel> model = new ArrayList<EmployeeResponseModel>();
        employees.forEach(s -> {EmployeeResponseModel employeeResponseModel = new EmployeeResponseModel(); BeanUtils.copyProperties(s, employeeResponseModel); model.add(employeeResponseModel);});
        return model;
    }
}
