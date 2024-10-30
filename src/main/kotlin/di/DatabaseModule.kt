package org.example.di

import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import com.mongodb.client.MongoDatabase
import dagger.Module
import dagger.Provides
import org.example.constants.Constants
import javax.inject.Named
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideMongoClient(
        @Named(Constants.MONGO_DB_HOST) host: String,
        @Named(Constants.MONGO_DB_PORT) port: Int
    ): MongoClient {
        return MongoClients.create("mongodb://$host:$port")
    }

    @Provides
    fun provideDatabase(
        mongoClient: MongoClient,
        @Named(Constants.MONGO_DB_NAME) dbName: String
    ): MongoDatabase {
        return mongoClient.getDatabase(dbName)
    }
}
