package com.uju.springbootrestapi.common

data class ResultResponse<T> (
    var data: T,
    var count: Int
)
