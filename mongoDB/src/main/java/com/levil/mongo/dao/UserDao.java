package com.levil.mongo.dao;

import com.google.common.collect.Lists;
import com.levil.mongo.entity.topicDocument;
import com.levil.mongo.entity.postDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserDao {
    @Autowired
    MongoTemplate mongoTemplate;

    @Transactional
    public String add(){
        postDocument postDocument = new postDocument();
        postDocument.setAge(22);
        postDocument.setPassword("22");
        postDocument.setUserName("22");
        postDocument.setAddress("北京22");
        topicDocument topicDocument = new topicDocument();
        topicDocument topicDocument2 = new topicDocument();
        topicDocument2.setTopicId("222");
        topicDocument2.setTopicName("322");

        topicDocument.setTopicId("122");
        topicDocument.setTopicName("222");
        postDocument.setTopicDocument(Lists.newArrayList(topicDocument,topicDocument2));

        com.levil.mongo.entity.postDocument afterInsert = mongoTemplate.insert(postDocument);
        return afterInsert.getId();
    }
}
