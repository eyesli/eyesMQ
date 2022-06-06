package com.levil.mongo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Data
@Document(collection = "post")
public class postDocument implements Serializable {

    private static final long serialVersionUID = -6572688910937746087L;

    @Id
    private String id;
    private String userName;
    private Integer age;
    private String password;
    private String address;
    private List<topicDocument> topicDocument;
}
