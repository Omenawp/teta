package com.oelrun.teta.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Profile(
    @PrimaryKey
    val userId: Int,
    val userName: String,
    val password: String,
    val phoneNumber: String?,
    val email: String,
    val photoUrl: Int?
)