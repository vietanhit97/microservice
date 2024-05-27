package com.vietanh.employeeserice.command.controller;

import java.util.UUID;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.vietanh.employeeserice.command.command.CreateEmployeeCommand;
import com.vietanh.employeeserice.command.command.DeleteEmployeeCommand;
import com.vietanh.employeeserice.command.command.UpdateEmployeeCommand;
import com.vietanh.employeeserice.command.model.EmployeeRequestModel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeCommandController {
    @Autowired CommandGateway commandGateway;
    @PostMapping
    //quá trình gửi đi comment
    public String addEmployee(@RequestBody EmployeeRequestModel model) {
        CreateEmployeeCommand employeeCommand = new CreateEmployeeCommand(UUID.randomUUID().toString(), model.getFirstName(), model.getLastName(), model.getKin(), true); 
        commandGateway.sendAndWait(employeeCommand);
        return "added Employee ";
    }

    @PutMapping
    public String updateBook(@RequestBody EmployeeRequestModel model) {
        UpdateEmployeeCommand updateEmployeeCommand = new UpdateEmployeeCommand(model.getEmployeeId(),model.getFirstName(),model.getLastName(),model.getKin(),true);
        commandGateway.sendAndWait(updateEmployeeCommand);
        return "updated Book";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable String id) {
        DeleteEmployeeCommand deleteEmployeeCommand = new DeleteEmployeeCommand(id);
        commandGateway.sendAndWait(deleteEmployeeCommand);
        return "deleted Book";
    }
}
