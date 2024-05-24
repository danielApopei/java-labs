package com.example.lab11.controllers;

import com.example.lab11.models.Book;
import com.example.lab11.models.requests.BookRequest;
import com.example.lab11.services.BookService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/books")
@Data
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(path="/all")
    public List<Book> getBooks() {
        return bookService.getAllBooks();
    }

    @PostMapping
    public ResponseEntity<String> createBook(@RequestBody BookRequest bookRequest) {
        return bookService.createBook(bookRequest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateBook(@PathVariable Long id, @RequestBody BookRequest bookRequest){
        return bookService.updateBook(id ,bookRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        return bookService.deleteBook(id);
    }

}
