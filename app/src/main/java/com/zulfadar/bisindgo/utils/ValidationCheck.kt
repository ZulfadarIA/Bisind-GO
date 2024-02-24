package com.zulfadar.bisindgo.utils

import android.util.Patterns

fun validateEmail(email: String): RegisterValidation{
    if (email.isEmpty())
        return RegisterValidation.Failed("Email tidak boleh kosong!")
    if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        return RegisterValidation.Failed("Format Email salah!")
    return RegisterValidation.Success
}

fun validatePassword(password: String): RegisterValidation{
    if (password.isEmpty())
        return RegisterValidation.Failed("Password tidak boleh kosong!")
    if (password.length < 8)
        return RegisterValidation.Failed("Password harus berisi minimal 8 karakter!")

    return RegisterValidation.Success
}