package com.example.personalprofile.data

data class Experience(
    val companyLogo: Int,
    val companyName: String,
    val projectName: String,
    val tecStack : List<String>,
    val jobRole: String,
    val duration: String
    )
