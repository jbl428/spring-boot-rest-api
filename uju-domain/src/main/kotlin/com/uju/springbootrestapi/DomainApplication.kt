package com.uju.springbootrestapi

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class DomainApplication {}

fun main(args: Array<String>) {
    runApplication<DomainApplication>(*args)
}