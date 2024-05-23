package com.vietanh.bookservice.command.data;

import org.springframework.data.jpa.repository.JpaRepository;
public interface BookReponsitory extends JpaRepository<Book,String> {
    
}
