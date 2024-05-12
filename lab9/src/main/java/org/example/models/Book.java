package org.example.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="books")
@NamedQueries({
        @NamedQuery(name="Book.findAll",
        query="select b from Book b order by b.title")
})
public class Book {
    private String title;
    private String authors;
    private Integer pages;
    private Date publicationDate;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY, generator = "id")
    @Column(name = "id")
    private Integer id;

    public Book(String title, String authors, Integer pages, Date publicationDate) {
        this.title = title;
        this.authors = authors;
        this.pages = pages;
        this.publicationDate = publicationDate;
    }

    public Book(String title, List<String> authors, Integer pages, Date publicationDate) {
        this.title = title;
        this.authors = String.join(" ", authors);
        this.pages = pages;
        this.publicationDate = publicationDate;
    }

    public Book() {
        this.authors = null;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return authors;
    }

    public void addAuthor(String author) {
        authors = authors+" "+author;
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

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
