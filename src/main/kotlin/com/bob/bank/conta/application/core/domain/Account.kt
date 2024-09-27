package com.bob.bank.conta.application.core.domain

import com.bob.bank.conta.adapters.repositories.entities.AccountEntity
import java.math.BigDecimal

abstract class Account {
    abstract val customerId: String
    abstract val accountNumber: String
    abstract var accountBalance: BigDecimal

    fun toDomain(): AccountEntity {
        return AccountEntity(
            customerId = customerId,
            accountNumber = accountNumber,
            accountBalance = accountBalance
        )
    }
}
