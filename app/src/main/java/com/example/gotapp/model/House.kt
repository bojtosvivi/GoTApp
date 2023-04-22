package com.example.gotapp.model

import kotlinx.serialization.Serializable

@Serializable
data class House (
    val slug: String,
    val name: String
)