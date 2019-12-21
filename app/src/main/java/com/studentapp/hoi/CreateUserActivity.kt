package com.studentapp.hoi

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

const val NEW_USER = "New User"

class CreateUserActivity: AppCompatActivity() {

    private var auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun createNewAccount(view: View) {
        val tag: String
        val email = findViewById<EditText>(R.id.emailField).text.toString()
        val password = findViewById<EditText>(R.id.passwordField).text.toString()
        val passwordCon = findViewById<EditText>(R.id.passwordConField).text.toString()
        if (password == passwordCon) {
            auth.createUserWithEmailAndPassword(email, password)
//            Toast.makeText(baseContext, auth.currentUser.toString(),
//                    Toast.LENGTH_SHORT).show()
            redirectToUserMenu()
        } else {
            Toast.makeText(baseContext, "Passwords do not match. Please try again.",
                    Toast.LENGTH_SHORT).show()
        }
    }

    private fun redirectToUserMenu() {
        val intent = Intent(this, UserMenu::class.java).apply {
            putExtra(NEW_USER, true)
        }
        startActivity(intent)
    }

    fun returnToMenu(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_user_layout)
    }
    //Check to see if user already logged in?
//    override fun onStart() {
//        super.onStart()
//        // Check if user is signed in (non-null) and update UI accordingly.
//        val currentUser = auth.currentUser
//        //updateUI(currentUser)
//    }
}