package com.zulfadar.bisindgo.data


data class User(
    val firstName: String,
    val lastName: String,
    val email: String,
    val telpNumb: String,
    var imagePath: String = ""
) {
    constructor(trim: String, trim1: String, trim2: String) : this("", "", "", "", "")
}
