package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

// The BootStrapData class creates some database entries on startup.

@Component // mark as Spring managed component
public class BootStrapData implements CommandLineRunner {

    // By implementing the CommandLineRunner the run method (below) will be executed after starting the application.

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository,
                         PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override // needs to be implemented (-> CommandLineRunner)
    public void run(String... args) throws Exception{

        System.out.println("Started in Bootstrap.");

        // Create new publisher instance
        Publisher oreilly = new Publisher("O'Reilly", "1005 Gravenstein Highway North", "Sebastopol",
                "California", "95472");

        // Save current state to the database
        publisherRepository.save(oreilly);

        // Create new author and book instance and couple them
        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Development", "123123");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        // Couple the book with the publisher instance
        ddd.setPublisher(oreilly); // Obviously not the 'correct' publisher
        oreilly.getBooks().add(ddd);

        // method chaining, add() is member of Set type -> retrieved member is immediately modified

        // Save current state to the database
        authorRepository.save(eric); // Spring uses Hibernate to save to an in-memory H2 database
        bookRepository.save(ddd);
        publisherRepository.save(oreilly);

        // Create new author and book instance and couple them
        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EEE Development without EJB", "234234");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);
        // Couple the book with the publisher instance
        noEJB.setPublisher(oreilly);
        oreilly.getBooks().add(noEJB);

        // Save current state to the database
        authorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(oreilly);

        System.out.println("Number of books: " + bookRepository.count());
        System.out.println("Number of publishers: " + publisherRepository.count());
        System.out.println("Publisher number of books: " + oreilly.getBooks().size());

    }
}
