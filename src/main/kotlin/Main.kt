package org.example
import di.ConfigModule
import org.example.di.DaggerAppComponent

fun main() {
//    val component = DaggerAppComponent.create() // Ensure this is the correct reference
//    val userService = component.getUserService()
//    val server = component.provideHttpServer() // Ensure this method exists in your DaggerComponent
////
////    // Start the server
//    server.start()
//    println("Server is running on http://localhost:8080/")





    // Provide the path to your XML configuration file
    val xmlFilePath = "/home/fretron/Downloads/Transportation_Management_System/mongoConfig.xml"

    // Create the ConfigModule with the XML file path
    val configModule = ConfigModule(xmlFilePath)

    // Create the Dagger component with the config module
    val component = DaggerAppComponent.factory().create(configModule)

    // Retrieve the services
    val userService = component.getUserService()
    val server = component.provideHttpServer()

    var consumer = component.activityLogConsumer().startConsuming();

    // Start the server
    server.start()
    println("Server is running on http://localhost:8080/")
}
