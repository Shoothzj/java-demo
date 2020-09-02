package com.github.shoothzj.demo.db.mongo;

import com.github.shoothzj.demo.base.test.module.TestUserDto;
import com.github.shoothzj.demo.base.test.util.TestDataUtil;
import com.github.shoothzj.javatool.util.EnvUtil;
import com.github.shoothzj.javatool.util.LogUtil;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;

import java.util.Arrays;

/**
 * @author hezhangjian
 */
@Slf4j
public class InsertTest {

    public static void main(String[] args) {
        LogUtil.configureLog();
        final String mongoAddr = EnvUtil.getStringVar("mongo.addr", "MONGO_ADDR", "localhost");
        final String mongoDatabase = EnvUtil.getStringVar("mongo.database", "MONGO_DATABASE", "ttbb");
        final String mongoCollection = EnvUtil.getStringVar("mongo.collection", "MONGO_COLLECTION", "user");
        final MongoClientSettings clientSettings =
                MongoClientSettings.builder().applyToClusterSettings(builder
                        -> builder.hosts(Arrays.asList(new ServerAddress(mongoAddr, 27017)))).build();
        final MongoClient mongoClient = MongoClients.create(clientSettings);
        final MongoDatabase database = mongoClient.getDatabase(mongoDatabase);
        final MongoCollection<Document> collection = database.getCollection(mongoCollection);

        final long startTime = System.currentTimeMillis();
        log.info("start time is [{}]", startTime);
        for (int i = 0; i < 100000; i++) {
            final TestUserDto userDto = TestDataUtil.generateTestUserDto();
            final Document locationDocument = new Document("country", "China");
            final Document document = new Document("userId", userDto.getUserId())
                    .append("age", userDto.getAge()).append("locationInfo", locationDocument);
            collection.insertOne(document);
        }
        log.info("cost is [{}]", System.currentTimeMillis() - startTime);
    }

}
