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

    @RequestMapping(value = {"/books"}, method=RequestMethod.GET)
    public String getBooks_crud(Model model) {
        List<Book> books = service.getBooks();
       model.addAttribute("book",new Book());
        model.addAttribute("books", books);
        return "books";
    }
    

    @RequestMapping(value = "create-book", method = RequestMethod.POST)
    public String createBookPost(@Valid Book book, BindingResult result, Model model) {
    	
    	if (result.hasErrors()) {
    		System.out.println(result.toString());
    		return "books";
    	}
    	
    	if(book.getId() == 0){
            //new book, add it
    		service.createBook(book);
        }else{
            //existing book, call update
        	service.editBook(book);
        }
    	
        
        return "redirect:/books";
    }
    
    @RequestMapping("/edit/{id}")
    public String editBook(@PathVariable("id") int id, Model model){
        model.addAttribute("book", this.service.getBookById(id));
        model.addAttribute("books", this.service.getBooks());
        return "books";
    }
    
    @RequestMapping("/remove/{id}")
    public String removeBook(@PathVariable("id") int id){
    	
    	this.service.removeBook(id);
    	return "redirect:/books";
    }    
    
  
}