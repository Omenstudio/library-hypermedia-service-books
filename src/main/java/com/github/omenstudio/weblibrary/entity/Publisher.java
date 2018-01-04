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

@HydraEntity(
        value = "http://schema.org/Publisher",
        pathToEntity = "/publishers/",
        pathToCollection = "/publishers/"
)
@Entity
@Table(name = "publishers")
public class Publisher {

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

    @HydraField("http://schema.org/foundingDate")
    @Column
    @Getter
    @Setter
    private Date foundingDate;

    @HydraField("http://schema.org/location")
    @Column
    @Getter
    @Setter
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

}
