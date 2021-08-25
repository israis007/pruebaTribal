package com.test.tribal.rest.objects

data class Resource<T>(
    var statusType  : StatusType          ,
    var data        : T?            = null,
    var message     : String?       = null
) {

    companion object {

        fun <T> success(data: T) : Resource<T> =
            Resource(StatusType.SUCCESS, data = data)

        fun <T> failed(data: T? = null) : Resource<T> =
            Resource(StatusType.FAILED, data = data)

        fun <T> loading() : Resource<T> =
            Resource(StatusType.LOADING)

        fun <T> error(message: String, data : T? = null) : Resource<T> =
            Resource(StatusType.ERROR, message = message, data = data)

    }

}