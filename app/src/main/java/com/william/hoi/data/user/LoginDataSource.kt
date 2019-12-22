package com.william.hoi.data.user

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.william.hoi.data.user.model.LoggedInUser
import javax.security.auth.login.LoginException
import kotlin.Exception

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {

    private var auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun login(email: String, password: String): Result<LoggedInUser> {
        return try {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "loginWithEmail:success")
                    } else {
                        Log.d(TAG, "loginWithEmail:failed")
                        throw LoginException("Unknown account or login error")
                    }
                }
            Result.Success(LoggedInUser(auth.currentUser!!.uid, email))
        } catch (exception: Exception) {
            Result.Error(exception)
        }
    }

//    fun createAccount(email: String, password: String): Result<LoggedInUser> {}

    fun logout() {
        auth.signOut()
    }
}
