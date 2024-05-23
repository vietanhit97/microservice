package com.vietanh.bookservice.command.event;

import lombok.Data;

@Data
public class BookCreatedEvent {
    private String id;
    private String name;
    private String author;
    private Boolean isReady;
}
