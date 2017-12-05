package com.github.omenstudio.weblibrary.entity;

import com.github.omenstudio.hydraback.annotation.HydraEntity;
import com.github.omenstudio.hydraback.annotation.HydraType;
import com.github.omenstudio.hydraback.annotation.JsonExclude;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@HydraEntity
@HydraType("http://schema.org/Publisher")
@Entity
@Table(name = "publishers")
public class Publisher {

    @JsonExclude
    @Id
    @GeneratedValue
    private Long id;

    @HydraType("http://schema.org/name")
    @Column(nullable = false)
    private String title;

    @HydraType("http://schema.org/foundingDate")
    @Column
    private Date foundingDate;

    @HydraType("http://schema.org/location")
    @Column
    private String location;

    @JsonExclude
    @OneToMany(mappedBy = "publisher", cascade = CascadeType.ALL)
    @Lazy
    private List<Book> books;

    public Publisher() { }

    public Publisher(String title, Date foundingDate, String location) {
        this.title = title;
        if (foundingDate != null) this.foundingDate = foundingDate;
        if (location != null) this.location = location;
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
