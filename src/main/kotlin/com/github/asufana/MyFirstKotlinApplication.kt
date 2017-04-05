package com.github.asufana

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class MyFirstKotlinApplication

fun main(args: Array<String>) {
    SpringApplication.run(MyFirstKotlinApplication::class.java, *args)
}
