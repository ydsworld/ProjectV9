package com.ydsworld.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 


import com.ydsworld.model.Book;
import com.ydsworld.service.BookRepository;

import java.util.List;
 
@Service
public class BookService {
 
    @Autowired
    protected BookRepository repository;
 
    public List<Book> getBooks() {
        return repository.getBooks();
    }
 
    public void createBook(Book book) {
        repository.createBook(book);
    }
    
    public void editBook(Book book) {
    	repository.editBook(book);
    }
    
    public Book getBookById(int id){
    	return repository.getBookById(id);
    }
    
    public void removeBook(int id){
    	repository.removeBook(id);
    }
}