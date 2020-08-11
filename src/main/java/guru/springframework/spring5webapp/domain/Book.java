package guru.springframework.spring5webapp.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity // defining JPA entity
public class Book {

    @Id // define this member as JPA identifier
    @GeneratedValue(strategy = GenerationType.AUTO) // ID will be assigned by database
    private Long id;

    private String title;
    private String isbn;

    @ManyToOne
    private Publisher publisher;

    @ManyToMany // describes the relationship between the POJOs / JPA entities
    @JoinTable(name = "author_book", joinColumns = @JoinColumn(name = "book_id"),
    inverseJoinColumns = @JoinColumn(name = "author_id"))
    private Set<Author> authors = new HashSet<>(); // initialize as empty set

    // JPA requires zero argument constructor
    public Book() {
    }

    public Book(String title, String isbn) {
        this.title = title;
        this.isbn = isbn;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o; // Brackets used as cast to tell compiler that assigning is ok

        return id != null ? id.equals(book.id) : book.id == null; // object/entity equality should be based upon the ID
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
