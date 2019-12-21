package com.studentapp.hoi

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class SignInActivity: AppCompatActivity() {

    private var auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun signIn(view: View) {
        var tag: String
        //Filter and prevent injection in future
        val email = findViewById<View>(R.id.emailField).toString()
        val password = findViewById<EditText>(R.id.passwordField).text.toString()
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(
                    baseContext,
                    "Please provide a valid email address and password",
                    Toast.LENGTH_SHORT
            ).show()
        } else {
            auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val user = auth.currentUser
                            val intent = Intent(this, UserMenu::class.java)
                            startActivity(intent)
                            //Update UI
                            //updateUI(user)
                        } else {
                            tag = "Failed"
                            // If sign in fails, display a message
                            Log.w(tag, "signInWithEmail:Failure", task.exception)
                            Toast.makeText(baseContext, "Authentication Failed",
                                    Toast.LENGTH_SHORT).show()
                            //Update UI on failure
                            //updateUI(null)
                        }
                    }
            //Testing only
            auth.signOut()
        }
    }

    fun returnToMenu(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
    //Check if user already signed in
//    override fun onStart() {
//        super.onStart()
//        val currentUser = auth.currentUser
//        if (currentUser != null) {
//            //Update UI accordingly
//            //updateUI(currentUser)
//        }
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_in_layout)
    }
}