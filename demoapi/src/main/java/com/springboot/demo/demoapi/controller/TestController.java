package com.springboot.demo.demoapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.demo.demoapi.entities.Book;
import com.springboot.demo.demoapi.service.BookService;

@RestController
public class TestController {
    @Autowired
    private BookService bookService;

    @RequestMapping("/book")
    public List<Book> getData()
    {
        return this.bookService.getAllBook();
    }

    @GetMapping("/book/{id}")
    public Book getBook(@PathVariable("id") int id)
    {
        return bookService.getBookById(id);

    }
    @PostMapping("/books")
    public Book addBook(@RequestBody Book book)
    {
       Book book2 =this.bookService.addBook(book);
        return book2;

    }

    
}
