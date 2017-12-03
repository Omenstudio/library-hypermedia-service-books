package com.github.omenstudio.weblibrary.controller;

import com.github.omenstudio.weblibrary.annotation.HydraGet;
import com.github.omenstudio.weblibrary.entity.Book;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/event2s/")
public class BookController {


    @HydraGet(path = "777")
    public Object getEvents() {
        return new Book();
    }


}
