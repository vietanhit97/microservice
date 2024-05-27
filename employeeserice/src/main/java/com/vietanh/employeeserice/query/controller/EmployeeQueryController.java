package com.vietanh.employeeserice.query.controller;

import org.springframework.web.bind.annotation.RestController;
import com.vietanh.employeeserice.query.model.EmployeeResponseModel;
import com.vietanh.employeeserice.query.queries.GetAllEmployeeQuery;
import com.vietanh.employeeserice.query.queries.GetEmployeeQuery;
import java.util.List;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping(("/api/v1/employees"))
public class EmployeeQueryController {
    @Autowired QueryGateway queryGateway;

    @GetMapping("/{id}")
    public EmployeeResponseModel getEmployeeDetail(@PathVariable String id) {
        GetEmployeeQuery query = new GetEmployeeQuery();
        query.setEmployeeId(id);
        EmployeeResponseModel responseModel = queryGateway.query(query, ResponseTypes.instanceOf(EmployeeResponseModel.class)).join();
        return responseModel;
    }
    
    @GetMapping("")
    public List<EmployeeResponseModel> getMethodName() {
        GetAllEmployeeQuery query = new GetAllEmployeeQuery();
        List<EmployeeResponseModel> responseModel = queryGateway.query(query, ResponseTypes.multipleInstancesOf(EmployeeResponseModel.class)).join(); 
        return  responseModel;
    }
    
}
