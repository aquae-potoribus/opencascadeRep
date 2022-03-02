package com.example.testthymleef;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProjectRepo extends MongoRepository<Project, Long> {
    Project findByid (Long id);


}
