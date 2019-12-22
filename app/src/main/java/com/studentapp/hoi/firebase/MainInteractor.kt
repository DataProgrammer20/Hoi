package com.studentapp.hoi.firebase

import com.google.firebase.FirebaseApp

class MainInteractor {
    fun getCurrentUsers() {
        val database: FirebaseApp = FirebaseApp.getInstance("https://hoi-24ecc.firebaseio.com/currentUsers")

    }
}