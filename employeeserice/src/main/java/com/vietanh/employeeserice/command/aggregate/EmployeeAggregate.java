package com.vietanh.employeeserice.command.aggregate;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import com.vietanh.employeeserice.command.command.CreateEmployeeCommand;
import com.vietanh.employeeserice.command.command.DeleteEmployeeCommand;
import com.vietanh.employeeserice.command.command.UpdateEmployeeCommand;
import com.vietanh.employeeserice.command.event.EmployeeCreateEvent;
import com.vietanh.employeeserice.command.event.EmployeeDeleteEvent;
import com.vietanh.employeeserice.command.event.EmployeeUpdateEvent;

import lombok.Data;
import lombok.NoArgsConstructor;

@Aggregate
@Data
@NoArgsConstructor
public class EmployeeAggregate {
    @AggregateIdentifier
    private String employeeId;
    private String firstName;
    private String lastName;
    private String kin;
    private Boolean isDisciplined;

    @CommandHandler
    public EmployeeAggregate(CreateEmployeeCommand createEmployeeCommand){
        EmployeeCreateEvent employeeCreateEvent = new EmployeeCreateEvent();
        BeanUtils.copyProperties(createEmployeeCommand, employeeCreateEvent);
        AggregateLifecycle.apply(employeeCreateEvent);
    }

    @CommandHandler
    public void handler(UpdateEmployeeCommand updateEmployeeCommand){
        EmployeeUpdateEvent employeeUpdateEvent = new EmployeeUpdateEvent();
        BeanUtils.copyProperties(updateEmployeeCommand, employeeUpdateEvent);
        AggregateLifecycle.apply(employeeUpdateEvent);
    }

    @CommandHandler 
    public void handler(DeleteEmployeeCommand deleteEmployeeCommand){
        EmployeeDeleteEvent employeeDeleteEvent = new EmployeeDeleteEvent();
        BeanUtils.copyProperties(deleteEmployeeCommand, employeeDeleteEvent);
        AggregateLifecycle.apply(employeeDeleteEvent);
    }

    @EventSourcingHandler
    public void on(EmployeeCreateEvent event){
        this.employeeId = event.getEmployeeId();
        this.firstName = event.getFirstName();
        this.lastName = event.getLastName();
        this.kin = event.getKin();
        this.isDisciplined = event.getIsDisciplined();
    }

    @EventSourcingHandler
    public void on(EmployeeUpdateEvent event){
        this.employeeId = event.getEmployeeId();
        this.firstName = event.getFirstName();
        this.lastName = event.getLastName();
        this.kin = event.getKin();
        this.isDisciplined = event.getIsDisciplined();
    }

    @EventSourcingHandler
    public void on(EmployeeDeleteEvent event){
        this.employeeId = event.getEmployeeId();
    }
}
