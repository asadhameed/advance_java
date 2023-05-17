package com.example.demo.books.controller;

import com.example.demo.books.model.Book;
import com.example.demo.books.respository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BookController {

    @Autowired
    private BookRepository bookRepository;

//
//    @QueryMapping
//    public List<Book> allBooks(){
//        return  bookRepository.findAll();
//    }


}
