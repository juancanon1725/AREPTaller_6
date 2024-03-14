package org.example;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Sorts;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MongoDBConnection {

    public static void insertLog(String message){
        try (MongoClient mongoClient = MongoClients.create("mongodb://mongo-db:27017/logs")){
            MongoDatabase db = mongoClient.getDatabase("logs");
            MongoCollection<Document> collection = db.getCollection("logs");
            Document logEntry = new Document();
            logEntry.append("mensaje", message);
            logEntry.append("fecha", new Date());
            collection.insertOne(logEntry);

        }
        catch (Exception e) {
            System.err.println("Error al insertar el registro en MongoDB: " + e.getMessage());
        }
    }

    public static List<String> getLast10Logs() {
        try (MongoClient mongoClient = MongoClients.create("mongodb://mongo-db:27017/logs")){
            MongoDatabase db = mongoClient.getDatabase("logs");
            MongoCollection<Document> collection = db.getCollection("logs");
            List<Document> ultimas10Cadenas = collection
                    .find()
                    .sort(Sorts.descending("fecha"))
                    .limit(10)
                    .into(new ArrayList<>());
            ArrayList<String> listLogs = new ArrayList<>();
            for (Document doc : ultimas10Cadenas) {
                String mensaje = doc.getString("mensaje");
                Date fecha = doc.getDate("fecha");
                listLogs.add(fecha + " " +   mensaje);
            }
            return listLogs;
        } catch (Exception e) {
            System.err.println("Error al obtener el registro en MongoDB: " + e.getMessage());

        }
        return null;
    }

}