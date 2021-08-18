package com.oelrun.teta.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Profile(
    @PrimaryKey
    val userId: Int,
    var userName: String,
    var password: String,
    var phoneNumber: String?,
    var email: String,
    val photoUrl: Int?
)