package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

// The BootStrapData class creates some database entries on startup.

@Component // mark as Spring managed component
public class BootStrapData implements CommandLineRunner {

    // By implementing the CommandLineRunner the run method (below) will be executed after starting the application.

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override // needs to be implemented (-> CommandLineRunner)
    public void run(String... args) throws Exception{

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Development", "123123");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        // method chaining, add() is member of Set type -> retrieved member is immediately modified

        authorRepository.save(eric); // Spring uses Hibernate to save to an in-memory H2 database
        bookRepository.save(ddd);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EEE Development without EJB", "234234");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        authorRepository.save(rod);
        bookRepository.save(noEJB);

        System.out.println("Started in Bootstrap.");
        System.out.println("Number of books: " + bookRepository.count());

    }
}
