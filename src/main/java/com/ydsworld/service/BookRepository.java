package com.ydsworld.service;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
 

import com.ydsworld.model.Book;

import java.util.List;
 
@Repository
@Transactional
public class BookRepository {
 
    @Autowired
    protected SessionFactory sessionFactory;
 
    @SuppressWarnings("unchecked")
    public List<Book> getBooks() {
        return sessionFactory.getCurrentSession()
                .createQuery("FROM Book")
                .list();
    }
 
    public void createBook(Book book) {
        sessionFactory.getCurrentSession().save(book);
    }
}