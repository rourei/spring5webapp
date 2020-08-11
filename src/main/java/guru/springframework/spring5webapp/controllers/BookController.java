package guru.springframework.spring5webapp.controllers;

import guru.springframework.spring5webapp.repositories.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

// Controller to return a list of all stored books to the view component

@Controller // Mark as Spring MVC controller
public class BookController {

    private final BookRepository bookRepository;

    // Instance of the book repository will be injected on construction
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @RequestMapping("/books") // request against url '/books' will invoke this method
    public String getBooks(Model model){

        model.addAttribute("books", bookRepository.findAll()); // add a list of all books to the model

        return "books"; // return the "view type"
    }
}
