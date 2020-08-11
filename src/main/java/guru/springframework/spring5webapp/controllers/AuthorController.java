package guru.springframework.spring5webapp.controllers;

import guru.springframework.spring5webapp.repositories.AuthorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

// Controller to return a list of all available authors to the view component

@Controller // Mark as Spring MVC controller
public class AuthorController {

    private final AuthorRepository authorRepository;

    // Instance of the author repository will be injected on construction
    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @RequestMapping("/authors") // request against url '/authors' will invoke this method
    public String getAuthors(Model model){

        model.addAttribute("authors", authorRepository.findAll()); // add a list of all authors to the model

        return "authors/list"; // return the "view type" specified in the templates.author directory
    }
}
