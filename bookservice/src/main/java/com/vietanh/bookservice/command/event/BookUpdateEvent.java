package com.vietanh.bookservice.command.event;

import lombok.Data;

@Data
public class BookUpdateEvent {
    private String id;
    private String name;
    private String author;
    private Boolean isReady;
}
