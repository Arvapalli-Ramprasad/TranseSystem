package org.example.di

import dagger.Component
import di.ConfigModule
import org.example.kafka.ActivityLogConsumer
import org.example.services.UserService
import org.example.repository.UserRepository
import org.glassfish.grizzly.http.server.HttpServer
import javax.inject.Singleton

@Singleton
@Component(modules = [DaggerMOdules::class, HttpModule::class, DatabaseModule::class, ConfigModule::class
,kafkaModule::class])
interface AppComponent {

    fun inject(userService: UserService)
    fun getUserRepository(): UserRepository
    fun getUserService(): UserService

    fun activityLogConsumer(): ActivityLogConsumer;

    fun provideHttpServer(): HttpServer

    @Component.Factory
    interface Factory {
        fun create(configModule: ConfigModule): AppComponent
    }


}