package org.taskmanager;

import com.mongodb.BasicDBObject;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Updates;
import org.bson.Document;

import com.mongodb.MongoException;
import com.mongodb.client.result.InsertOneResult;
import org.bson.conversions.Bson;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class Database {
    public static String uri = "mongodb+srv://mgoodall:AILSa27UGi5jFvqD@cluster0.wg6k1fu.mongodb.net/";

    public void initiatePoints() {
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("TaskManager686");
            MongoCollection<Document> collection = database.getCollection("Points");

            Document query = collection.find(Filters.eq("User", "default")).first();

            if (query == null) {
                Document document = new Document("User", "default")
                        .append("points", 0);
                collection.insertOne(document);
            }
        }
    }
    public void sendData(String desc, String due, Difficulty diff, int id) {

        Document document = new Document("Task", desc)
                .append("Due Date", due)
                .append("Difficulty", diff)
                .append("ID", id);
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("TaskManager686");
            MongoCollection<Document> collection = database.getCollection("Tasks");
            collection.insertOne(document);
        }


    }

    public void deleteTask(Task t) {
        Document document = new Document("Task", t.getDescription())
                .append("Due Date", t.getDueDate().toString())
                .append("Difficulty", t.getDifficulty())
                .append("ID", t.getId())
                .append("Completed", t.getDateCompleted().toString());

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("TaskManager686");
            MongoCollection<Document> collection = database.getCollection("Tasks");
            MongoCollection<Document> archivedCollection = database.getCollection("ArchivedTasks");

            archivedCollection.insertOne(document);
            collection.deleteOne(Filters.eq("ID",t.getId()));

        }
    }

    public void modTask(String desc, String due, Difficulty diff, int id) {
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("TaskManager686");
            MongoCollection<Document> collection = database.getCollection("Tasks");

            collection.updateOne(new Document("ID", id),
                    new Document("Task", desc)
                            .append("Due Date", due)
                            .append("Difficulty", diff));


        }
    }

    public LinkedList<Task> returnTasks(String coll) {
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            LinkedList<Task> taskLinkedList = new LinkedList<>();
            MongoDatabase database = mongoClient.getDatabase("TaskManager686");
            MongoCollection<Document> collection = database.getCollection(coll);
            FindIterable<Document> iterTask = collection.find();
            MongoCursor<Document> cursor = iterTask.iterator();

            while (cursor.hasNext()) {
                Document doc = cursor.next();
                String description = doc.getString("Task");
                System.out.println(description + " descrt");
                DueDate dueDate = new DueDate();
                String dueDateStr = doc.getString("Due Date");
                dueDate.toData(dueDateStr);

                String difficultyStr = doc.getString("Difficulty");

                int id = doc.getInteger("ID");
                System.out.println(id + " ID");

                Task t = new Task(description, dueDate, Difficulty.valueOf(difficultyStr), id);
                if (coll.equals("ArchivedTasks")) {
                    DueDate completed = new DueDate();
                    String completedString = doc.getString("Completed");
                    completed.toData(completedString);
                    t.setDateCompleted(completed);
                }
                taskLinkedList.add(t);

            }
            return taskLinkedList;

        } catch (Exception e) {
            System.out.println("Failed to retrieve tasks");
        }
        return null;
    }

    public JTable displayCompletedTasks() {
        LinkedList<Task> taskLinkedList = returnTasks("ArchivedTasks");
        String[] columnNames = {"Task", "Date Completed", "Points Awarded"};
        if (taskLinkedList != null) {
            String[][] data = new String[taskLinkedList.size()][3];
            for (int i = 0; i < taskLinkedList.size(); i++) {
                Task t = taskLinkedList.get(i);
                data[i][0] = t.getDescription();
                data[i][1] = t.getDateCompleted().toString();
                PointCalculator pc = new PointCalculator(t);
                data[i][2] = String.valueOf(pc.setPointsFromTask());
            }
            JTable jTable = new JTable(data, columnNames);
            return jTable;
        }
        return null;
    }

    public int findPoints() {
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("TaskManager686");
            MongoCollection<Document> collection = database.getCollection("Points");
            Document document = collection
                    .find(new BasicDBObject("User", "default"))
                    .projection(Projections.fields(Projections.include("points"), Projections.excludeId())).first();
            Integer score = document.getInteger("points");
            if (score != null) {
                System.out.println(score);
                return score;
            } else {
                return 0;
            }

        } catch (Exception e) {
            System.out.println("Failed to retrieve points");
        }
        return 0;
    }
    public void updatePoints(Task t) {
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("TaskManager686");
            PointCalculator pc = new PointCalculator();
            pc.assignTask(t);
            int newPoints = pc.setPointsFromTask();
            Bson updates = Updates.inc("points", newPoints);
            database.getCollection("Points").updateOne(Filters.eq("User", "default"), updates);
            Document result = database.getCollection("Points").find(Filters.eq("User", "default")).first();
            if (result != null) {
                System.out.println("Retrieved Document:\n" + result.toJson());
            } else {
                System.out.println("No documents found.");
            }

        }
    }

}
