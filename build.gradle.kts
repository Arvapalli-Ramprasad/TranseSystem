//plugins {
//    kotlin("jvm") version "1.6.10"
//    kotlin("kapt") version "1.6.10"
//}
//
//group = "org.example"
//version = "1.0-SNAPSHOT"
//
//repositories {
//    mavenCentral()
//}
//
//dependencies {
//    implementation(kotlin("stdlib"))
//
//    // Dagger dependencies
//    implementation("com.google.dagger:dagger:2.44") // Update to the latest stable version
//    kapt("com.google.dagger:dagger-compiler:2.44")
//
//    // Jersey dependencies
//    implementation("org.glassfish.jersey.containers:jersey-container-grizzly2-http:2.39")
//    implementation("org.glassfish.jersey.core:jersey-server:2.39")
//    implementation("org.glassfish.jersey.media:jersey-media-json-jackson:2.39")
//
//    // Jackson dependencies
//    implementation("com.fasterxml.jackson.core:jackson-databind:2.14.1")
//    implementation("com.fasterxml.jackson.core:jackson-core:2.14.1")
//    implementation("com.fasterxml.jackson.core:jackson-annotations:2.14.1")
//
//    // MongoDB driver
//    implementation("org.mongodb:mongodb-driver-sync:4.7.0")
//
//    // Test dependencies
//    testImplementation(kotlin("test"))
//}
//
//
//tasks.test {
//    useJUnitPlatform()
//}
//
////kotlin {
////    jvmToolchain(19)
////}






//##########################################################################\

plugins {
    kotlin("jvm") version "1.8.0"
    kotlin("kapt") version "1.8.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))

    // Dagger dependencies
    implementation("com.google.dagger:dagger:2.44")
    kapt("com.google.dagger:dagger-compiler:2.44")

    // kafka dependencies
    implementation("org.apache.kafka:kafka-clients:3.3.1")

    // Jersey dependencies
    implementation("org.glassfish.jersey.containers:jersey-container-grizzly2-http:2.39")
    implementation("org.glassfish.jersey.core:jersey-server:2.39")
    implementation("org.glassfish.jersey.media:jersey-media-json-jackson:2.39")


    // Jackson dependencies
    implementation("com.fasterxml.jackson.core:jackson-databind:2.14.1")
    implementation("com.fasterxml.jackson.core:jackson-core:2.14.1")
    implementation("com.fasterxml.jackson.core:jackson-annotations:2.14.1")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.14.0") // Use the latest version


    // MongoDB driver
    implementation("org.mongodb:mongodb-driver-sync:4.7.0")

    implementation("org.glassfish.jersey.inject:jersey-hk2:2.39")

    implementation("org.mongodb:mongodb-driver-sync:4.6.1")



    // Test dependencies
    testImplementation(kotlin("test"))

    // JSON processing libraries
    implementation("org.json:json:20170516")

    // https://mvnrepository.com/artifact/com.google.code.gson/gson
    implementation("com.google.code.gson:gson:2.8.6")

}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "17" // Align Kotlin with Java 17
    }
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17) // Align JVM Toolchain with Java 17
}
