package com.ydsworld.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ydsworld.model.Book;
import com.ydsworld.service.BookService;

@Controller
public class BookControllerV1 {

	@Autowired
    protected BookService service;
	
	    @RequestMapping(value = {"/booksV1"}, method=RequestMethod.GET)
    public String getBooks_crud(Model model) {
        List<Book> books = service.getBooks();
        model.addAttribute("book",new Book());
        model.addAttribute("books", books);
        return "booksV1";
    }
    
    
    @RequestMapping(value = "create-bookV1", method = RequestMethod.POST)
    public String createBookPost(@Valid Book book, BindingResult result, Model model) {
    	
    	//model.addAttribute("error","False");
    	
    	if (result.hasErrors()) {
    		model.addAttribute("error","True");
    		return "booksV1";
    	}
    	
    	if(book.getId() == 0){
            //new book, add it
    		service.createBook(book);
        }else{
            //existing book, call update
        	service.editBook(book);
        }
    	
        
        return "redirect:/booksV1";
    }
    
    @RequestMapping("/editV1/{id}")
    public String editBook(@PathVariable("id") int id, Model model){
        model.addAttribute("book", this.service.getBookById(id));
        model.addAttribute("books", this.service.getBooks());
        return "booksV1";
    }
    
    
    @RequestMapping("/removeV1/{id}")
    public String removeBook(@PathVariable("id") int id){
    	
    	this.service.removeBook(id);
    	return "redirect:/booksV1";
    }    
    
    
    
}
