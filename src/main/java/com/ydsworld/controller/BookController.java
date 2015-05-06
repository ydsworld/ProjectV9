package com.ydsworld.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
 

import com.ydsworld.model.Book;
import com.ydsworld.model.Form;
import com.ydsworld.service.BookService;

import java.util.List;

import javax.validation.Valid;
 
@Controller
public class BookController {
 
    @Autowired
    protected BookService service;

   
  /*  //list of books with option to add new book
    @RequestMapping(value = {"/*", "/books"})
    public String getBooks(Model model) {
        List<Book> books = service.getBooks();
        model.addAttribute("books", books);
        return "books";
    }*/
    
    
    //list of books with CRUD
    @RequestMapping(value = {"/*", "/books_crud"}, method=RequestMethod.GET)
    public String getBooks_crud(Model model) {
        List<Book> books = service.getBooks();
       model.addAttribute("book",new Book());
        model.addAttribute("books", books);
        return "books_crud";
    }
    
    @RequestMapping(value = "create-book")
    public String createBookGet(Model model) {
       model.addAttribute("book", new Book());
        return "create-book";
    }
 

    @RequestMapping(value = "create-book", method = RequestMethod.POST)
    public String createBookPost(@Valid @ModelAttribute("book") Book book, BindingResult result) {
    	
    	if (result.hasErrors()) {
    		System.out.println(result.toString());
    		return "redirect:books_crud";
    	}
    	
    	if(book.getId() == 0){
            //new book, add it
    		service.createBook(book);
        }else{
            //existing book, call update
        	service.editBook(book);
        }
    	
        
        return "redirect:books_crud";
    }
    
    @RequestMapping("/edit/{id}")
    public String editBook(@PathVariable("id") int id, Model model){
        model.addAttribute("book", this.service.getBookById(id));
        model.addAttribute("books", this.service.getBooks());
        return "books_crud";
    }
    
    @RequestMapping("/remove/{id}")
    public String removeBook(@PathVariable("id") int id){
    	
    	this.service.removeBook(id);
    	return "redirect:/books_crud";
    }    
    
  
}