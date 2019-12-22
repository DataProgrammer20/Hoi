package com.william.hoi.data.user

import com.google.firebase.auth.FirebaseAuth
import com.william.hoi.data.user.model.LoggedInUser

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository(val dataSource: LoginDataSource) {

    private var auth: FirebaseAuth = FirebaseAuth.getInstance()

    val isLoggedIn: Boolean
        // check if logged in
        get() = auth.currentUser != null

    fun logout() {
        // handle logout
        dataSource.logout()
    }

    fun login(username: String, password: String): Result<Any> {
        // handle login
        return dataSource.login(username, password)
    }
}
