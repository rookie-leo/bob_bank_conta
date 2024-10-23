package com.bob.bank.conta.adapters.`in`.controllers

import com.bob.bank.conta.application.ports.`in`.CreateAccountInputPort
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/accounts")
class CreateAccountController(
    val accountInputPort: CreateAccountInputPort
) {

    @PostMapping("/create/{customerId}")
    fun createAccount(@PathVariable customerId: String): ResponseEntity<Any> {
        return try {
            val accounts = accountInputPort.createAccount(customerId)
            ResponseEntity.status(HttpStatus.CREATED).body(accounts)
        } catch (ex: RuntimeException) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.message)
        }
    }

}