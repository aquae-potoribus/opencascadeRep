package com.example.OCCtask1.Repository;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
@Getter
@Setter
public class Project {

    @Id
    private String id;
    private String name;

}
