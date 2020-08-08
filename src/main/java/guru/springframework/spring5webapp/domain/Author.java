package guru.springframework.spring5webapp.domain;

import javax.persistence.*;
import java.util.Set;

@Entity // defining JPA entity
public class Author {

    @Id // define this member as JPA identifier
    @GeneratedValue(strategy = GenerationType.AUTO) // ID will be assigned by database
    private Long id;

    private String firstName;
    private String lastName;

    @ManyToMany(mappedBy = "authors") // describes the relationship between the POJOs / JPA entities
    private Set<Book> books;

    // JPA requires zero argument constructor
    public Author() {
    }

    public Author(String firstName, String lastName, Set<Book> books) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.books = books;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }
}
