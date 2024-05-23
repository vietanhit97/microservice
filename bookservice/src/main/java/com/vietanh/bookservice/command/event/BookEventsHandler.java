package com.vietanh.bookservice.command.event;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vietanh.bookservice.command.data.Book;
import com.vietanh.bookservice.command.data.BookReponsitory;

@Component
public class BookEventsHandler {
    @Autowired BookReponsitory bookReponsitory;

    @EventHandler
    public void on(BookCreatedEvent event) {
        Book book = new Book();
        BeanUtils.copyProperties(event, book);
        bookReponsitory.save(book);
    }
}
