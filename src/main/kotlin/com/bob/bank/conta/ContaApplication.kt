package com.bob.bank.conta

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ContaApplication

fun main(args: Array<String>) {
	runApplication<ContaApplication>(*args)
}
