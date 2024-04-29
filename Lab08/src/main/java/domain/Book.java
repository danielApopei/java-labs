package domain;

import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Book {
    public String title;
    public List<Author> authors;
    public String language;
    public Date publicationDate;
    public int pageCount;
    public Book(String title, List<Author> authors, String language, Date publicationDate, int pageCount) {
        this.title = title;
        this.authors = authors;
        this.language = language;
        this.publicationDate = publicationDate;
        this.pageCount = pageCount;
    }
    public Book(String title, List<Author> authors, String language, String publicationDate, int pageCount) {
        this.title = title;
        this.authors = authors;
        this.language = language;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            this.publicationDate = dateFormat.parse(publicationDate);
        } catch (ParseException e) {
            System.err.println("Error parsing the date. Please use the format dd-MM-yyyy.");
            this.publicationDate = null;
        }
        this.pageCount = pageCount;
    }
}
