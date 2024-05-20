package com.zulfadar.bisindgo.data


data class User(
    val firstName: String,
    val lastName: String,
    val email: String,
    val telpNumb: String,
    var imagePath: String = ""
) {
    constructor() : this("", "", "", "", "")

    constructor(firstName: String, lastName: String, email: String, telpNumb: String) : this(firstName, lastName, email, telpNumb, "")
}
