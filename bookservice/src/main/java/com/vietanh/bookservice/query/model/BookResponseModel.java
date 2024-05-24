package com.vietanh.bookservice.query.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookResponseModel {
    private String id;
    private String name;
    private String author;
    private Boolean isReady;
}
