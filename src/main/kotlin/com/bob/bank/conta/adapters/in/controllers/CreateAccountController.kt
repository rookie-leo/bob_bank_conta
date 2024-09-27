package com.bob.bank.conta.adapters.`in`.controllers

import com.bob.bank.conta.adapters.`in`.controllers.request.AccountRequest
import com.bob.bank.conta.adapters.`in`.controllers.response.AccountResponse
import com.bob.bank.conta.application.core.domain.Account
import com.bob.bank.conta.application.ports.`in`.CreateAccountInputPort
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/accounts")
class CreateAccountController(
    val accountInputPort: CreateAccountInputPort
) {

    @PostMapping("/create")
    fun createAccount(@Valid @RequestBody request: AccountRequest): List<Account> {
        return accountInputPort.createAccount(request)
    }

}