package com.example.OCCtask1;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProjectRepo extends MongoRepository<Project, Long> {
    Project findByid (Long id);


}
