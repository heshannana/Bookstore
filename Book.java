package com.resit.sdgp.bookstore;


public class Book {
    private String id;
    private String bookName;
    private int rating;

    public Book() {

    }

    public Book(String id, String bookName, int rating) {
        this.bookName = bookName;
        this.rating = rating;
        this.id = id;
    }

    public String getTrackName() {
        return bookName;
    }

    public int getRating() {
        return rating;
    }
}
