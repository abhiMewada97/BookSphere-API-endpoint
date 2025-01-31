package com.abhishek.bookStore.repository;

import com.abhishek.bookStore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    Book findByIsbn(Integer isbn);
    void deleteByIsbn(Integer isbn);

    @Query("SELECT b from Book b WHERE "+
            "LOWER(b.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(b.author) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(b.bookGenres) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Book> searchBook(String keyword);

    @Query("SELECT b FROM Book b WHERE " +
            "(COALESCE(:author, NULL) IS NULL OR b.author IN :author) AND " +
            "(COALESCE(:bookGenres, NULL) IS NULL OR b.bookGenres IN :bookGenres) AND " +
            "b.price BETWEEN :minPrice AND :maxPrice")
    List<Book> filterBook(List<String>author, List<String>bookGenres, Integer minPrice, Integer maxPrice);
}
