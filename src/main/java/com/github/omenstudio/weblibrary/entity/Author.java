package com.github.omenstudio.weblibrary.entity;


import com.github.omenstudio.hydra.annotation.JsonExclude;
import com.github.omenstudio.hydra.annotation.model.HydraEntity;
import com.github.omenstudio.hydra.annotation.model.HydraField;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

//import java.util.Date;


@HydraEntity(
        value = "http://schema.org/Person",
        pathToEntity = "/authors/",
        pathToCollection = "/authors/"
)
@Entity
@Table(name = "authors")
public class Author {

    @JsonExclude
    @Id
    @GeneratedValue
    @Getter
    @Setter
    private Long id;

    @HydraField(value = "http://schema.org/name", includeInCollection = true)
    @Column(nullable = false)
    @Getter
    @Setter
    private String name;

    @HydraField("http://schema.org/birthDate")
    @Column
    @Getter
    @Setter
    private Date birthDate;

    @HydraField("http://schema.org/birthPlace")
    @Column
    @Getter
    @Setter
    private String birthPlace;

    @JsonExclude
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    @Lazy
    private List<Book> books;

    public Author() { }

    public Author(String name, Date birthDate, String birthPlace) {
        this.name = name;
        if (birthDate != null) this.birthDate = birthDate;
        if (birthPlace != null) this.birthPlace = birthPlace;
    }

}
