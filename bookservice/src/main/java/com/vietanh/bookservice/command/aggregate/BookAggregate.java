package com.vietanh.bookservice.command.aggregate;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;
import com.vietanh.bookservice.command.command.CreateBookCommand;
import com.vietanh.bookservice.command.command.DeleteBookCommand;
import com.vietanh.bookservice.command.command.UpdateBookCommand;
import com.vietanh.bookservice.command.event.BookCreatedEvent;
import com.vietanh.bookservice.command.event.BookDeleteEvent;
import com.vietanh.bookservice.command.event.BookUpdateEvent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Aggregate
@Data
@NoArgsConstructor
public class BookAggregate {
    @AggregateIdentifier
    private String id;
    private String name;
    private String author;
    private Boolean isReady;

    @CommandHandler
    public BookAggregate(CreateBookCommand bookCommand){
        BookCreatedEvent bookCreatedEvent = new BookCreatedEvent();
        BeanUtils.copyProperties(bookCommand, bookCreatedEvent);
        AggregateLifecycle.apply(bookCreatedEvent);
    }

    @CommandHandler
    public void handle(UpdateBookCommand updateBookCommand){
        System.out.println( "CommandHandler");
        BookUpdateEvent bookUpdateEvent = new BookUpdateEvent();
        BeanUtils.copyProperties(updateBookCommand, bookUpdateEvent);
        AggregateLifecycle.apply(bookUpdateEvent);
    }

    @CommandHandler
    public void handle(DeleteBookCommand deleteBookCommand){
        BookDeleteEvent bookDeleteEvent = new BookDeleteEvent();
        BeanUtils.copyProperties(deleteBookCommand, bookDeleteEvent);
        AggregateLifecycle.apply(bookDeleteEvent);
    }
    
    @EventSourcingHandler
    public void on(BookCreatedEvent bookCreatedEvent){
        this.id = bookCreatedEvent.getId();
        this.name = bookCreatedEvent.getName();
        this.author = bookCreatedEvent.getAuthor();
        this.isReady = bookCreatedEvent.getIsReady();
    }

    @EventSourcingHandler
    public void on(UpdateBookCommand updateBookCommand){
        System.out.println( "EventSourcingHandler");
        this.id = updateBookCommand.getId();
        this.name = updateBookCommand.getName();
        this.author = updateBookCommand.getAuthor();
        this.isReady = updateBookCommand.getIsReady();
    }

    @EventSourcingHandler
    public void on(DeleteBookCommand deleteBookCommand){
        this.id = deleteBookCommand.getId();
    }
}
