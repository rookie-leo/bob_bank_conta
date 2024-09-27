package com.bob.bank.conta.application.ports.out

import com.bob.bank.conta.application.core.domain.Account

interface CustomerOutPutPort {
    fun createAccount(accounts: List<Account>)
}
