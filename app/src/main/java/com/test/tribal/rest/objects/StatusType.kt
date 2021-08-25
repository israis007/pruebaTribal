package com.test.tribal.rest.objects

enum class StatusType {
    SUCCESS,
    FAILED,
    ERROR,
    LOADING;

    fun isLoading() = this == LOADING
}