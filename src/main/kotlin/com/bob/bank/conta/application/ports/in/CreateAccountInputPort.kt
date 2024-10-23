package com.bob.bank.conta.application.ports.`in`

import com.bob.bank.conta.application.core.domain.Account

interface CreateAccountInputPort {
    fun createAccount(customerId: String): List<Account>
}
