package com.studentapp.hoi.userData

data class Message(val message: String = "",
                   val timeStamp: Long = System.currentTimeMillis()
)