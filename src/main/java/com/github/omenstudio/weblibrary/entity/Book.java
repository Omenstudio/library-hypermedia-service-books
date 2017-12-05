package com.github.omenstudio.weblibrary.entity;

import com.github.omenstudio.hydraback.annotation.HydraEntity;
import com.github.omenstudio.hydraback.annotation.HydraType;
import com.github.omenstudio.hydraback.annotation.JsonExclude;

import javax.persistence.*;
import java.util.Set;

@HydraEntity
@HydraType("http://schema.org/Book")
@Entity
@Table(name = "books")
public class Book {

    @JsonExclude
    @Id
    @GeneratedValue
    private Long id;

    @HydraType("http://schema.org/headline")
    @Column(nullable = false)
    private String title;

    @HydraType("http://schema.org/alternativeHeadline")
    @Column
    private String originalTitle;

    @HydraType("http://schema.org/description")
    @Column
    private String description;

    @HydraType("http://schema.org/copyrightYear")
    @Column
    private Integer copyrightYear;

    @HydraType("http://schema.org/bookEdition")
    @Column
    private Integer bookEdition;

    @HydraType("http://schema.org/numberOfPages")
    @Column
    private Integer numberOfPages;

    @HydraType("http://schema.org/isbn")
    @Column
    private String isbn;

    @ManyToMany
    @JoinTable(
            name = "authors_books",
            joinColumns = { @JoinColumn(name = "book_id") },
            inverseJoinColumns = { @JoinColumn(name = "author_id") }
    )
    private Set<Author> authors;

    @ManyToOne
    private Publisher publisher;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCopyrightYear() {
        return copyrightYear;
    }

    public void setCopyrightYear(Integer copyrightYear) {
        this.copyrightYear = copyrightYear;
    }

    public Integer getBookEdition() {
        return bookEdition;
    }

    public void setBookEdition(Integer bookEdition) {
        this.bookEdition = bookEdition;
    }

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }
}
