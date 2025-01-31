package com.abhishek.bookStore.model;

import java.util.List;

public class SearchBookModel {

    private List<String> author;
    private List<String> bookGenres;
    private Integer minPrice;
    private Integer maxPrice;

    public List<String> getAuthor() {
        return author;
    }

    public void setAuthor(List<String> author) {
        this.author = author;
    }

    public List<String> getBookGenres() {
        return bookGenres;
    }

    public void setBookGenres(List<String> bookGenres) {
        this.bookGenres = bookGenres;
    }

    public Integer getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Integer minPrice) {
        this.minPrice = minPrice;
    }

    public Integer getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Integer maxPrice) {
        this.maxPrice = maxPrice;
    }
}
