package com.example.gotapp.model

import com.example.gotapp.R
import kotlinx.serialization.Serializable

@Serializable
data class House (
    val slug: String,
    val name: String
) {
    fun getCrest(): Int {
        return when (slug) {
            "baratheon" -> R.drawable.baratheon
            "bolton" -> R.drawable.bolton
            "greyjoy" -> R.drawable.greyjoy
            "lannister" -> R.drawable.lannister
            "martell" -> R.drawable.martell
            "stark" -> R.drawable.stark
            "targaryen" -> R.drawable.targaryen
            "tully" -> R.drawable.tully
            "tyrell" -> R.drawable.tyrell
            else -> R.drawable.crest_placeholder
        }
    }
}