package org.example.di

import com.mongodb.client.MongoDatabase
import dagger.Provides
import javax.inject.Singleton
import dagger.Module
import org.example.kafka.ActivityLogConsumer
import org.example.kafka.ActivityLogProducer
import javax.inject.Named

@Module
class kafkaModule {

    @Provides
    @Singleton
    fun provideKafkaActivityLogProducer(@Named("kafka.bootstrap.servers") bootstrapServers: String,
                                        @Named("kafka.username") username: String,
                                        @Named("kafka.password") password: String,
                                        @Named("kafka.topic") topic: String): ActivityLogProducer{

        return ActivityLogProducer(bootstrapServers,username,password,topic);
    }

    @Provides
    @Singleton
    fun provideKafkaActivityLogConsumer(database: MongoDatabase,
                                        @Named("kafka.bootstrap.servers")  bootstrapServers: String,
                                        @Named("kafka.username")  username: String,
                                        @Named("kafka.password") password: String,
                                        @Named("kafka.topic") topic: String): ActivityLogConsumer{

        return ActivityLogConsumer(database,bootstrapServers,username,password,topic);
    }


}