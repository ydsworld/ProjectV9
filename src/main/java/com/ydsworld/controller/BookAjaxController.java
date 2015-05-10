package com.ydsworld.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ydsworld.model.Book;
import com.ydsworld.service.BookService;

@Controller
public class BookAjaxController {

	@Autowired
    protected BookService service;
	
	 @RequestMapping(value = {"/booksAjax"}, method=RequestMethod.GET)
	    public String getBooks_crud(Model model) {
	        List<Book> books = service.getBooks();
	        model.addAttribute("book",new Book());
	        model.addAttribute("books", books);
	        return "booksAjax";
	   }
	 
	
	@RequestMapping(value = "/create-bookAjax", method=RequestMethod.POST)
	@ResponseBody
	public String createBookAjax(@Valid Book book, BindingResult result, Model model,HttpServletRequest request){
//model.addAttribute("error","False");
    	book.setTitle(request.getParameter("title"));
    	book.setAuthor(request.getParameter("author"));
		
    	if (result.hasErrors()) {
    		return "See the error below";
    	} else {
    	
    	if(book.getId() == 0){
            //new book, add it
    		service.createBook(book);
        }else{
            //existing book, call update
        	service.editBook(book);
        }
    	
        
        return "Success";
    	}
	}
	
	
	 @RequestMapping("/editAjax/{id}")
	    public String editBook(@PathVariable("id") int id, Model model){
	        model.addAttribute("book", this.service.getBookById(id));
	        model.addAttribute("books", this.service.getBooks());
	        return "booksAjax";
	    }
	    
	    
	    @RequestMapping("/removeAjax/{id}")
	    public String removeBook(@PathVariable("id") int id){
	    	
	    	this.service.removeBook(id);
	    	return "redirect:/booksAjax";
	    }    
}
