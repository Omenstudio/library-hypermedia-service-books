package com.github.omenstudio.weblibrary.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.omenstudio.hydra.annotation.JsonExclude;
import com.github.omenstudio.hydra.annotation.model.HydraEntity;
import com.github.omenstudio.hydra.annotation.model.HydraField;
import com.github.omenstudio.hydra.annotation.model.HydraLink;
import com.github.omenstudio.weblibrary.utils.BookJsonDeserializer;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@HydraEntity(
        value = "http://schema.org/Book",
        pathToEntity = "/books/",
        pathToCollection = "/books/"
)
@Entity
@JsonDeserialize(using = BookJsonDeserializer.class)
@Table(name = "books")
public class Book {

    @JsonExclude
    @Id
    @GeneratedValue
    @Getter
    @Setter
    private Long id;

    @HydraField(value = "http://schema.org/headline", includeInCollection = true)
    @Column(nullable = false)
    @Getter
    @Setter
    private String title;

    @HydraField("http://schema.org/alternativeHeadline")
    @Column
    @Getter
    @Setter
    private String originalTitle;

    @HydraField("http://schema.org/description")
    @Column
    @Getter
    @Setter
    private String description;

    @HydraField("http://schema.org/copyrightYear")
    @Column
    @Getter
    @Setter
    private Integer copyrightYear;

    @HydraField("http://schema.org/bookEdition")
    @Column
    @Getter
    @Setter
    private Integer bookEdition;

    @HydraField("http://schema.org/numberOfPages")
    @Column
    @Getter
    @Setter
    private Integer numberOfPages;

    @HydraField("http://schema.org/isbn")
    @Column
    @Getter
    @Setter
    private String isbn;

    @HydraLink(value = "http://schema.org/Person", includeInCollection = true)
    @JsonIgnore
    @ManyToOne
    @Getter
    @Setter
    private Author author;

    @HydraLink(value = "http://schema.org/Publisher", includeInCollection = true)
    @JsonIgnore
    @ManyToOne
    @Getter
    @Setter
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

}
