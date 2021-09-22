package com.walker.finalappdotaheros.models

import com.google.gson.annotations.SerializedName


data class Hero(
    @SerializedName( "id")
    val id: Int,
    @SerializedName("localized_name")
    val localized_name: String,
    @SerializedName("primary_attr")
    val primary_attr: String,
    @SerializedName("attack_type")
    val attack_type: String,
    @SerializedName("img")
    val img: String,
    @SerializedName("roles")
    val roles: ArrayList<String>
)