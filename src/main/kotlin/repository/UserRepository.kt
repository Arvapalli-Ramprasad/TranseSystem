//package org.example.UserRepository
//import com.mongodb.client.MongoDatabase
//import org.example.di.DatabaseModule
//import org.example.model.User
//import java.util.concurrent.ConcurrentHashMap
//import javax.inject.Inject
//
//
//class UserRepository @Inject constructor(private val database: MongoDatabase) {
//
//    private val collection MongoCollection<Document> = databaseModule.getCollection(User_Info)
//
//    private val userStore = ConcurrentHashMap<String, User>();
//
//
//    fun save(user: User) {
//        userStore[user.userId] = user;
//    }
//
//    fun findAll(): List<User> {
//        return userStore.values.toList() // Return all users as a list
//    }
//}

package org.example.repository

import com.google.gson.Gson
import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import com.mongodb.client.model.Filters
import org.apache.kafka.common.protocol.types.Field
import org.bson.Document
import org.example.di.DatabaseModule
import org.example.kafka.ActivityLogProducer
import org.example.model.User

import java.sql.Timestamp
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap
import javax.inject.Inject
import javax.inject.Named
import javax.management.Query.eq

class UserRepository @Inject constructor(private val database: MongoDatabase,
    private val activityLogProducer: ActivityLogProducer) {



    private val collection: MongoCollection<Document> = database.getCollection("User_Info") // Assuming "User_Info" is the collection name

    private val userStore = ConcurrentHashMap<String, User>()

    fun save(user: User) {

        userStore[user.userId!!] = user // Ensure userId is not null when saving
        // Optionally, also save to MongoDB
        val document = Document("userId", user.userId)
            .append("name", user.name)
            .append("email", user.email)

        collection.insertOne(document) // Save to MongoD


        var log_id = UUID.randomUUID();
        var createdAt = Timestamp(System.currentTimeMillis())
        // Create a document for LogActivity
        val logActivityDocument = Document()
            .append("description", "New student is Created")
            .append("log_id", log_id)
            .append("createdAt", createdAt)
            .append("userId", user.userId)

        // Convert logActivityDocument to JSON string using Gson
        val gson = Gson()
        val logMessage = gson.toJson(logActivityDocument)

        // Send the log message to Kafka
        activityLogProducer.sendLog(logMessage)

    }

    fun findAll(): List<User> {
        return userStore.values.toList() // Return all users as a list
    }

    fun findById(id: String): User? {
//        var time = Timestamp(System.currentTimeMillis())
        val document = collection.find(Filters.eq("userId", id)).first() ?: return null
        return User(
            userId = document.getString("userId"),
            name = document.getString("name"),
            email = document.getString("email"),
            password = document.getString("password")
        )
    }
}

