package com.github.omenstudio.weblibrary.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.omenstudio.hydraback.annotation.HydraEntity;
import com.github.omenstudio.hydraback.annotation.HydraField;
import com.github.omenstudio.hydraback.annotation.HydraLink;
import com.github.omenstudio.hydraback.annotation.JsonExclude;
import com.github.omenstudio.weblibrary.utils.BookJsonDeserializer;

import javax.persistence.*;

@HydraEntity("http://schema.org/Book")
@Entity
@JsonDeserialize(using = BookJsonDeserializer.class)
@Table(name = "books")
public class Book {

    @JsonExclude
    @Id
    @GeneratedValue
    private Long id;

    @HydraField("http://schema.org/headline")
    @Column(nullable = false)
    private String title;

    @HydraField("http://schema.org/alternativeHeadline")
    @Column
    private String originalTitle;

    @HydraField("http://schema.org/description")
    @Column
    private String description;

    @HydraField("http://schema.org/copyrightYear")
    @Column
    private Integer copyrightYear;

    @HydraField("http://schema.org/bookEdition")
    @Column
    private Integer bookEdition;

    @HydraField("http://schema.org/numberOfPages")
    @Column
    private Integer numberOfPages;

    @HydraField("http://schema.org/isbn")
    @Column
    private String isbn;

    @HydraLink("http://schema.org/Person")
    @JsonIgnore
    @ManyToOne
    private Author author;

    @HydraLink("http://schema.org/Publisher")
    @JsonIgnore
    @ManyToOne
    private Publisher publisher;

    public Book() { }

    public Book(String title, String originalTitle, String description, Integer copyrightYear, Integer bookEdition, Integer numberOfPages, String isbn) {
        this.title = title;
        if (originalTitle != null) this.originalTitle = originalTitle;
        if (description != null) this.description = description;
        if (copyrightYear != null) this.copyrightYear = copyrightYear;
        if (bookEdition != null) this.bookEdition = bookEdition;
        if (numberOfPages != null) this.numberOfPages = numberOfPages;
        if (isbn != null) this.isbn = isbn;
    }

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

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
