package com.ydsworld.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table
public class Book {
    
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
 

	@Size(min=5, max=10, message="Your name should be between 5 - 10 characters.")
    private String title;
 
    @NotEmpty
    private String author;
 
   /* public Book(int id, String title, String author){
    	this.id =id;
    	this.title=title;
    	this.author=author;
    }*/
    
    
    public int getId() {
        return id;
    }
 
    public void setId(int id) {
        this.id = id;
    }
 
    public String getTitle() {
        return title;
    }
 
    public void setTitle(String title) {
        this.title = title;
    }
 
    public String getAuthor() {
        return author;
    }
 
    public void setAuthor(String author) {
        this.author = author;
    }
}
