package com.vietanh.employeeserice.query.model;

import lombok.Data;

@Data
public class EmployeeResponseModel {
    private String employeeId;
    private String firstName;
    private String lastName;
    private String kin;
    private Boolean isDisciplined;
}
