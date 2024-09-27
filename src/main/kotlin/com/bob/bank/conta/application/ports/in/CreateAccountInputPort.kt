package com.bob.bank.conta.application.ports.`in`

import com.bob.bank.conta.adapters.`in`.controllers.request.AccountRequest
import com.bob.bank.conta.application.core.domain.Account

interface CreateAccountInputPort {
    fun createAccount(accountRequest: AccountRequest): List<Account>
}
