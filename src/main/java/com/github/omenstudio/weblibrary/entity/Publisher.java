package com.github.omenstudio.weblibrary.entity;

import com.github.omenstudio.weblibrary.annotation.HydraEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@HydraEntity
public class Publisher {
    @Id
    @GeneratedValue
    long id;
}
