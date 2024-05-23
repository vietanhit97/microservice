package com.vietanh.bookservice.command.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "books")
@Data
public class Book {
    @Id
    private String id;
    private String name;
    private String author;
    private Boolean isReady;
}
