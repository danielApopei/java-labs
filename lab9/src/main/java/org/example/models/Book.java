package org.example.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name="book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Author> authors = new ArrayList<>();

    private Integer pages;

    @Temporal(TemporalType.DATE)
    private Date publicationDate;

    // Constructors, getters, and setters

    public Book() {
    }

    public Book(String title, Integer pages, Date publicationDate) {
        this.title = title;
        this.pages = pages;
        this.publicationDate = publicationDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Author> getAuthor() {
        return authors;
    }

    public void addAuthor(Author author) {
        authors.add(author);
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", authors=" + authors +
                ", pages=" + pages +
                ", publicationDate=" + publicationDate +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }



    public String getIAuthor(int i) {
        //List<String> l = List.of(this.authors.split(";"));
        return "get(i)";
    }

    public int authorCount() {
        return authors.size();
    }
}
