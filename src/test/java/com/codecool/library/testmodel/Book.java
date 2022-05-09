package com.codecool.library.testmodel;

import java.util.Objects;

public class Book {
    private String title;
    private String writerName;
    private String genre;
    private int numberOwnedByLibrary;
    private int numberBorrowed;

    public Book(String title, String writerName, String genre, int numberOwnedByLibrary, int numberBorrowed) {
        this.title = title;
        this.writerName = writerName;
        this.genre = genre;
        this.numberOwnedByLibrary = numberOwnedByLibrary;
        this.numberBorrowed = numberBorrowed;
    }

    public Book() {}

    public String getTitle() {
        return title;
    }

    public String getWriterName() {
        return writerName;
    }

    public String getGenre() {
        return genre;
    }

    public int getNumberOwnedByLibrary() {
        return numberOwnedByLibrary;
    }

    public int getNumberBorrowed() {
        return numberBorrowed;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", writerName='" + writerName + '\'' +
                ", genre='" + genre + '\'' +
                ", numberOwnedByLibrary=" + numberOwnedByLibrary +
                ", numberBorrowed=" + numberBorrowed +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return numberOwnedByLibrary == book.numberOwnedByLibrary &&
                numberBorrowed == book.numberBorrowed &&
                Objects.equals(title, book.title) &&
                Objects.equals(writerName, book.writerName) &&
                Objects.equals(genre, book.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, writerName, genre, numberOwnedByLibrary, numberBorrowed);
    }
}
