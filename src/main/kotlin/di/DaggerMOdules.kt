package org.example.di

import com.mongodb.client.MongoDatabase
import dagger.Module
import dagger.Provides
import org.example.kafka.ActivityLogProducer
import org.example.repository.UserRepository
import org.example.services.UserService

@Module
class DaggerMOdules {

    @Provides
    fun provideUserRepository(database: MongoDatabase , activityLogProducer: ActivityLogProducer):UserRepository{
        return UserRepository(database,activityLogProducer)
    }

    @Provides
    fun provideUserService(userRepository:UserRepository):UserService{
        return UserService(userRepository)
    }

}