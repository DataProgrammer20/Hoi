package com.william.hoi.data.user

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.william.hoi.data.user.model.LoggedInUser
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {

    // Firebase auth object
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun login(email: String, password: String): Result<LoggedInUser> {
        return try {
            var user: LoggedInUser? = null
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "loginWithEmail:success")
                        user = LoggedInUser(
                            auth.currentUser!!.uid,
                            auth.currentUser!!.email.toString()
                        )
                    }
                }
            Result.Success(user)
        } catch (exception: Throwable) {
            Result.Error(
                IOException(
                    "Error logging in",
                    exception
                )
            )
        }
    }

    fun logout() {
        auth.signOut()
    }
}

