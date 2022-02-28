package com.example.testthymleef;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProjectRepo extends MongoRepository<Project, Long> {
}
