package com.zulfadar.bisindgo.ui.dialog

import android.app.Activity
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.dicoding.projecttapenerjemahbahasaisyaratindonesia.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog

fun Activity.setupBottomSheetDialog(onSendClick: (String) -> Unit) {
    val dialog = BottomSheetDialog(this, R.style.DialogStyle)
    val view = layoutInflater.inflate(R.layout.reset_password_bottom_dialog, null)
    dialog.setContentView(view)
    dialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
    dialog.show()

    val edtEmail = view.findViewById<EditText>(R.id.edt_reset_password)
    val buttonSave = view.findViewById<Button>(R.id.btnSaveResetPAssword)
    val buttonCancel = view.findViewById<Button>(R.id.btnCancelResetPassword)

    buttonSave.setOnClickListener {
        val email = edtEmail.text.toString().trim()
        onSendClick(email)
        dialog.dismiss()
    }

    buttonCancel.setOnClickListener {
        dialog.dismiss()
    }
}