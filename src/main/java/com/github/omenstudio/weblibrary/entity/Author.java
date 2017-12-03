package com.github.omenstudio.weblibrary.entity;

import com.github.omenstudio.weblibrary.annotation.HydraEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@HydraEntity
public class Author {
    @Id
    @GeneratedValue
    long id;

    @Column(nullable = false)
    String name;

    @Column
    Date birthDate;


}
