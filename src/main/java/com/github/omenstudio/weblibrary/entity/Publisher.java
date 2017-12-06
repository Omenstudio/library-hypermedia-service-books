package com.github.omenstudio.weblibrary.entity;

import com.github.omenstudio.hydraback.annotation.HydraEntity;
import com.github.omenstudio.hydraback.annotation.HydraField;
import com.github.omenstudio.hydraback.annotation.JsonExclude;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@HydraEntity("http://schema.org/Publisher")
@Entity
@Table(name = "publishers")
public class Publisher {

    @JsonExclude
    @Id
    @GeneratedValue
    private Long id;

    @HydraField("http://schema.org/name")
    @Column(nullable = false)
    private String name;

    @HydraField("http://schema.org/foundingDate")
    @Column
    private Date foundingDate;

    @HydraField("http://schema.org/location")
    @Column
    private String location;

    @JsonExclude
    @OneToMany(mappedBy = "publisher", cascade = CascadeType.ALL)
    @Lazy
    private List<Book> books;

    public Publisher() { }

    public Publisher(String name, Date foundingDate, String location) {
        this.name = name;
        if (foundingDate != null) this.foundingDate = foundingDate;
        if (location != null) this.location = location;
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

    public Date getFoundingDate() {
        return foundingDate;
    }

    public void setFoundingDate(Date foundingDate) {
        this.foundingDate = foundingDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
