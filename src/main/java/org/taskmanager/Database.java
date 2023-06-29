package org.taskmanager;

import org.bson.Document;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;

public class Database {

    public void addToDatabase() {
        String uri = "mongodb+srv://mgoodall:AILSa27UGi5jFvqD@cluster0.wg6k1fu.mongodb.net/";

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("cmsc495");
            MongoCollection<Document> collection = database.getCollection("taskmanager");
            try {
                InsertOneResult result = collection.insertOne(new Document()
                        .append("_id", "124"));
                System.out.println("Success! Inserted document id: " + result.getInsertedId());
            } catch (MongoException me) {
                System.err.println("Unable to insert due to an error: " + me);
            }
        }
    }

}
