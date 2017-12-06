package com.github.omenstudio.weblibrary.controller;


import com.github.omenstudio.hydraback.annotation.request.HydraDeleteRequest;
import com.github.omenstudio.hydraback.annotation.request.HydraGetRequest;
import com.github.omenstudio.hydraback.annotation.request.HydraPostRequest;
import com.github.omenstudio.hydraback.annotation.request.HydraPutRequest;
import com.github.omenstudio.weblibrary.entity.Publisher;
import com.github.omenstudio.weblibrary.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/publishers/")
public class PublisherController {

    @Autowired
    PublisherRepository publisherRepository;

    @HydraGetRequest
    public Object getPublishers() {
        return publisherRepository.findAll();
    }

    @HydraPostRequest
    public Object createPublisher(@RequestBody Publisher publisher) {
        return publisherRepository.save(publisher);
    }

    @HydraGetRequest("/{publisherId}")
    public Object getPublisher(@PathVariable Long publisherId) {
        return publisherRepository.findOne(publisherId);
    }

    @HydraPutRequest("/{publisherId}")
    public Object changePublisher(@PathVariable Long publisherId, @RequestBody Publisher publisher) {
        publisher.setId(publisherId);
        return publisherRepository.save(publisher);
    }

    @HydraDeleteRequest("/{publisherId}")
    public void deletePublisher(@PathVariable Long publisherId) {
        publisherRepository.delete(publisherId);
    }

}