package com.abhishek.bookStore.controller;

import com.abhishek.bookStore.model.Book;
import com.abhishek.bookStore.model.SearchBookModel;
import com.abhishek.bookStore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping("/add")
    public ResponseEntity<String> addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @GetMapping("/get/{isbn}")
    public ResponseEntity<Book> getBookByIsbn(@PathVariable Integer isbn) {
        return bookService.getBookByIsbn(isbn);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Book>> getAllBook() {
        return bookService.getAllBook();
    }

    @PutMapping("/update/{isbn}")
    public ResponseEntity<String> updateBook(@PathVariable Integer isbn, @RequestBody Book book) {
        return bookService.updateBook(isbn, book);
    }

    @DeleteMapping("/delete/{isbn}")
    public ResponseEntity<String> deleteBook(@PathVariable Integer isbn) {
        return bookService.deleteBook(isbn);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchBook(@RequestParam String keyword) {
        return bookService.searchBook(keyword);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Book>> filterBook(@RequestBody SearchBookModel book) {
        return bookService.filterBook(book);
    }
}

