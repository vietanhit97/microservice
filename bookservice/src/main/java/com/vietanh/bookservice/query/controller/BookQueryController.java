package com.vietanh.bookservice.query.controller;

import org.springframework.web.bind.annotation.RestController;
import com.vietanh.bookservice.query.model.BookResponseModel;
import com.vietanh.bookservice.query.queries.GetAllBooksQuery;
import com.vietanh.bookservice.query.queries.GetBookQuery;
import java.util.List;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/v1/books")
public class BookQueryController {
    @Autowired private QueryGateway queryGateway;

    @GetMapping("/{id}")
    public BookResponseModel getBookDetail(@PathVariable String id) {
        GetBookQuery query = new GetBookQuery();
        query.setId(id);
        BookResponseModel bookResponseModel = queryGateway.query(query, ResponseTypes.instanceOf(BookResponseModel.class)).join();
        return bookResponseModel;
    }

    @GetMapping("")
    public List<BookResponseModel> getListBook() {
        GetAllBooksQuery allBooksQuery = new GetAllBooksQuery();
        List<BookResponseModel> list = queryGateway.query(allBooksQuery, ResponseTypes.multipleInstancesOf(BookResponseModel.class)).join();
        return list;
    }
    
}
