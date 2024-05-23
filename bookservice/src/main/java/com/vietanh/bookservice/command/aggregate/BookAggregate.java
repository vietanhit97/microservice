package com.vietanh.bookservice.command.aggregate;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;
import com.vietanh.bookservice.command.command.CreateBookCommand;
import com.vietanh.bookservice.command.event.BookCreatedEvent;
import lombok.Data;

@Aggregate
@Data
public class BookAggregate {
    @TargetAggregateIdentifier
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
    
    @EventSourcingHandler
    public void on(BookCreatedEvent bookCreatedEvent){
        this.id = bookCreatedEvent.getId();
        this.name = bookCreatedEvent.getName();
        this.author = bookCreatedEvent.getAuthor();
        this.isReady = bookCreatedEvent.getIsReady();
    }
}
