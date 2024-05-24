package com.vietanh.bookservice.command.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteBookCommand {
    @TargetAggregateIdentifier
    private String id;
}
