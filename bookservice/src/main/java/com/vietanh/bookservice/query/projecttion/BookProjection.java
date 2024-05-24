package com.vietanh.bookservice.query.projecttion;

import java.util.ArrayList;
import java.util.List;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.vietanh.bookservice.command.data.Book;
import com.vietanh.bookservice.command.data.BookReponsitory;
import com.vietanh.bookservice.query.model.BookResponseModel;
import com.vietanh.bookservice.query.queries.GetAllBooksQuery;
import com.vietanh.bookservice.query.queries.GetBookQuery;

@Component
public class BookProjection {
    @Autowired private BookReponsitory bookReponsitory;

    @QueryHandler
    public BookResponseModel handle(GetBookQuery bookQuery){
        BookResponseModel model = new BookResponseModel();
        Book book = bookReponsitory.getById(bookQuery.getId());
        BeanUtils.copyProperties(book, model);
        return model;
    }

    @QueryHandler
    public List<BookResponseModel> handle(GetAllBooksQuery allBooksQuery){
        List<Book> books = bookReponsitory.findAll();
        List<BookResponseModel> bookResponseModels = new ArrayList<BookResponseModel>();
        books.forEach(s -> {BookResponseModel model = new BookResponseModel(); BeanUtils.copyProperties(s, model); bookResponseModels.add(model); });
        return bookResponseModels;
    }
}
