package com.springboot.demo.demoapi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.springboot.demo.demoapi.entities.Book;


@Component

public class BookService {

    
        private static List<Book> list=new ArrayList<>();

        static
        {
            list.add(new Book(12, "Arun", "9784555"));
            list.add(new Book(13, "ankit", "9784555"));
            list.add(new Book(14, "test", "9784555"));
            list.add(new Book(15, "Arun", "978555"));

        }
        

        public List<Book> getAllBook()
        {
            return list;

        }
    

        public Book getBookById(int id)
        {
            Book book=null;
                book=list.stream().filter(e->e.getId()==id).findFirst().get();
                return book;
        }
        //ading the book
        public Book addBook( Book book)
        {
            list.add(0, book);
            return book;
        }
    
}
