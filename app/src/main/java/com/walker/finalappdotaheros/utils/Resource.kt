package com.walker.finalappdotaheros.utils

enum class Status {
    SUCCESS, ERROR, LOADING
}

data class Resource<out T>(val status: Status, val data: T? = null, val message: String? = "") {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }
        fun <T> error(message: String?): Resource<T> {
            return Resource(Status.ERROR, null, message)
        }
    }
}