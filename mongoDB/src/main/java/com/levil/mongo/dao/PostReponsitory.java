package com.levil.mongo.dao;

import com.levil.mongo.entity.postDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostReponsitory extends MongoRepository<postDocument,Long> {
}