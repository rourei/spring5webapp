package guru.springframework.spring5webapp.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity // defining JPA entity
public class Author {

    @Id // define this member as JPA identifier
    @GeneratedValue(strategy = GenerationType.AUTO) // ID will be assigned by database
    private Long id;

    private String firstName;
    private String lastName;

    @ManyToMany(mappedBy = "authors") // describes the relationship between the POJOs / JPA entities
    private Set<Book> books = new HashSet<>(); // initialize as empty set

    // JPA requires zero argument constructor
    public Author() {
    }

    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
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

    @Override // method overrides parent's method (if parent doesn't contain this method -> compiler error)
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Author author = (Author) o; // Brackets used as cast to tell compiler that assigning is ok

        return id != null ? id.equals(author.id) : author.id == null; // equality should be based upon the ID
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
