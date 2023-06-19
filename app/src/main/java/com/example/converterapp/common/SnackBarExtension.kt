package com.example.converterapp.common

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.showSnackBar(errorMessage : String){
    Snackbar.make(this, errorMessage, Snackbar.LENGTH_SHORT).show()
}
fun View.hide(){
    this.visibility = View.GONE
}

fun View.show(){
    this.visibility = View.VISIBLE
}