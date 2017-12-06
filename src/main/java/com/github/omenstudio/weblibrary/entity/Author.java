package com.github.omenstudio.weblibrary.entity;

import com.github.omenstudio.hydraback.annotation.HydraEntity;
import com.github.omenstudio.hydraback.annotation.HydraField;
import com.github.omenstudio.hydraback.annotation.JsonExclude;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@HydraEntity("http://schema.org/Person")
@Entity
@Table(name = "authors")
public class Author {

    @JsonExclude
    @Id
    @GeneratedValue
    private Long id;

    @HydraField("http://schema.org/name")
    @Column(nullable = false)
    private String name;

    @HydraField("http://schema.org/birthDate")
    @Column
    private Date birthDate;

    @HydraField("http://schema.org/birthPlace")
    @Column
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

}
