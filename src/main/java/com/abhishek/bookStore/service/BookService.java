package com.abhishek.bookStore.service;

import com.abhishek.bookStore.model.Book;
import com.abhishek.bookStore.model.SearchBookModel;
import com.abhishek.bookStore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepo;

    public ResponseEntity<String> addBook(Book book) {
        try {
            Integer isbn  = book.getIsbn();
            Book book1 = bookRepo.findByIsbn(isbn);
            if(book1 != null) {
                return new ResponseEntity<>("Already Available", HttpStatus.BAD_REQUEST);
            }
            bookRepo.save(book);
            return new ResponseEntity<>("Successfully added", HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Book> getBookByIsbn(Integer isbn) {
        try {
            Book book = bookRepo.findByIsbn(isbn);
            if(book != null) {
                return new ResponseEntity<>(book, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Book>> getAllBook() {
        try {
            return new ResponseEntity<>(bookRepo.findAll(), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<String> updateBook(Integer isbn, Book book) {
       try {
           Book book1 = bookRepo.findByIsbn(isbn);
           if(book1 != null) {
               if( isbn.equals(book.getIsbn())) {
                    bookRepo.save(book);
                    return new ResponseEntity<>("Book updated", HttpStatus.OK);
               }
               return new ResponseEntity<>("Book ISBN can not changed", HttpStatus.OK);
           }
           else {
               return new ResponseEntity<>("Failed to update", HttpStatus.BAD_REQUEST);
           }
       }
       catch (Exception e) {
           return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

    public ResponseEntity<String> deleteBook(Integer isbn) {
        try {
            Book book = bookRepo.findByIsbn(isbn);
            if(book != null) {
//                bookRepo.deleteByIsbn(isbn);
                bookRepo.deleteById(isbn);
                return new ResponseEntity<>("Book deleted", HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>("Book Not Found", HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e) {
            return new ResponseEntity<>("Failed to delete", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Book>> searchBook(String keyword) {
        List<Book> books = bookRepo.searchBook(keyword);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    public ResponseEntity<List<Book>> filterBook(SearchBookModel book) {
        List<Book> books = bookRepo.filterBook(book.getAuthor(), book.getBookGenres(), book.getMinPrice(), book.getMaxPrice());
        return new ResponseEntity<>(books, HttpStatus.OK);
    }
}
