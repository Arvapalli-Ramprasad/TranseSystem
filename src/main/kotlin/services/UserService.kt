package org.example.services

import org.example.model.User
import org.example.repository.UserRepository
import javax.inject.Inject

class UserService @Inject constructor(private val userRepository: UserRepository){

    fun createUser(user: User){
        return userRepository.save(user)
    }

    fun getAllUsers(): List<User> {
        return userRepository.findAll() // Fetch all users from the repository
    }

    fun getUserByUserId(userId:String): User?{
        return userRepository.findById(userId);
    }
}