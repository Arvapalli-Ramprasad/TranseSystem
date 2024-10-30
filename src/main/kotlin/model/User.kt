package org.example.model

import org.json.JSONObject

import java.sql.Timestamp

data class User(
    val userId: String? = null,
    val name: String? = null,
    val email: String? = null,
    val password: String? = null,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is User) return false

        return userId == other.userId
    }

    override fun hashCode(): Int {
        return userId?.hashCode() ?: 0
    }

    override fun toString(): String {
        return "User(userId=$userId, name=$name, email=$email)"
    }
}

data class LogActivity(
    var description: String?,
    var log_id: String?,
    var createdAt: Timestamp?,
    var userId: String?
) {
    constructor() : this(
        description = null,
        log_id = null,
        createdAt = null,
        userId = null
    )

    override fun toString(): String {
        return JSONObject()
            .put("description", this.description ?: JSONObject.NULL)
            .put("log_id", this.log_id ?: JSONObject.NULL)
            .put("createdAt", this.createdAt ?: JSONObject.NULL)
            .put("userId", this.userId ?: JSONObject.NULL)
            .toString()
    }
}