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


}
