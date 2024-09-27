package com.bob.bank.conta.application.core.domain

import java.math.BigDecimal

data class SavingsAccount(
    override val customerId: String,
    override val accountNumber: String,
    override var accountBalance: BigDecimal = BigDecimal("0.0")
): Account()