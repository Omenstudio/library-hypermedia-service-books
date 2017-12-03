package com.github.omenstudio.weblibrary.entity;

import com.github.omenstudio.weblibrary.annotation.HydraEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@HydraEntity(pathId = "/events/")
public class Book {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "title", nullable = false)
    private String title;

    public Book() {
        this.id = 777;
        this.title = "Три топора";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
