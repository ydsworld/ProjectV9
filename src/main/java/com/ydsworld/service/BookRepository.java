package com.ydsworld.service;

import org.hibernate.Session;
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
    
    public void editBook(Book book) {
    	sessionFactory.getCurrentSession().update(book);
    }
    
    public Book getBookById(int id) {
        Session session = this.sessionFactory.getCurrentSession();      
        Book b = (Book) session.get(Book.class, new Integer(id));
       // logger.info("Person loaded successfully, Person details="+p);
        return b;
    }
    
    public void removeBook(int id){
    	Session session = this.sessionFactory.getCurrentSession();
    	Book b = (Book) session.get(Book.class, new Integer(id));
    	if(null !=b){
    		session.delete(b);
    	}
    }
    
    
}