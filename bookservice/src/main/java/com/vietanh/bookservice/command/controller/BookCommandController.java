package com.vietanh.bookservice.command.controller;

import org.springframework.web.bind.annotation.RestController;

import com.vietanh.bookservice.command.command.CreateBookCommand;
import com.vietanh.bookservice.command.model.BookRequestModel;
import java.util.UUID;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/v1/books")
public class BookCommandController {
    @Autowired CommandGateway commandGateway;
    @PostMapping
    //quá trình gửi đi comment
    public String addBook(@RequestBody BookRequestModel model) {
        CreateBookCommand bookCommand = new CreateBookCommand(UUID.randomUUID().toString(),model.getAuthor(),model.getName(),true);
        commandGateway.sendAndWait(bookCommand);
        return "added Book";
    }
    
}
