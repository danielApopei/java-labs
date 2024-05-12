package org.example.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name="book")
public class Book {
    private String title;
    private String authors;
    private Integer pages;
    private Date publicationDate;
    private Integer Id;
    @javax.persistence.Id
    private Long id;

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

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }



    public String getIAuthor(int i) {
        List<String> l = List.of(this.authors.split(";"));
        return l.get(i);
    }

    public int authorCount() {
        return authors.split(";").length;
    }
}
