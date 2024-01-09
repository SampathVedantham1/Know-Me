package com.example.personalprofile.data

data class Project(
    val projectLogo: Int,
    val projectName: String,
    val projectStack: List<String>,
    val projectDetails: String,
    val contributors: String,
    val reference: String
)
