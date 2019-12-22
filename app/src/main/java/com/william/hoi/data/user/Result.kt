package com.william.hoi.data.user

import com.william.hoi.data.user.model.LoggedInUser

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class Result<out T : Any> {

    data class LoginActionResult(val loggedIn: Boolean, var userData: LoggedInUser) : Result<Boolean>()
    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is LoginActionResult -> "Login Result[status=$loggedIn"
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
        }
    }
}
