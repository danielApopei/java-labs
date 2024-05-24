package com.example.lab11.services;

import com.example.lab11.models.Book;
import com.example.lab11.models.requests.BookRequest;
import com.example.lab11.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository  bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public ResponseEntity<String> createBook(BookRequest bookRequest) {
        Book book = new Book(bookRequest.getTitle(), bookRequest.getAuthor());
        bookRepository.save(book);
        return ResponseEntity.ok("Book created");
    }

    public ResponseEntity<String> deleteBook(Long id) {
        bookRepository.deleteById(id);
        return ResponseEntity.ok("Book deleted");
    }

    public ResponseEntity<String> updateBook(Long id, BookRequest bookRequest) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book == null) {
            return ResponseEntity.badRequest().body("Book not found");
        }
        book.setTitle(bookRequest.getTitle());
        book.setAuthor(bookRequest.getAuthor());
        bookRepository.save(book);
        return ResponseEntity.ok("Book updated");
    }
}
