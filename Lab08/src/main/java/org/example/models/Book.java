package org.example.models;

import java.util.Date;
import java.util.List;

public class Book {
    private String title;
    private List<String> authors;
    private Integer pages;
    private Date publicationDate;

    public Book(String title, List<String> authors, Integer pages, Date publicationDate) {
        this.title = title;
        this.authors = authors;
        this.pages = pages;
        this.publicationDate = publicationDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAuthor() {
        return authors;
    }

    public void addAuthor(String author) {
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
}
