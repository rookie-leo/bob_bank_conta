package com.bob.bank.conta.adapters.`in`.controllers.request

import jakarta.validation.constraints.NotBlank

data class AccountRequest(

    @field: NotBlank
    val customerId: String,

    @field: NotBlank
    val customerCpf: String
)
