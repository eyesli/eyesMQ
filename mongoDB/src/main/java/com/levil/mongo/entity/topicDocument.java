package com.levil.mongo.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "topic")
public class topicDocument {
    private String topicId;
    private String topicName;
}
