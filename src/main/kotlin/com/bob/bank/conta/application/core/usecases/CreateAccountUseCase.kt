package com.bob.bank.conta.application.core.usecases

import com.bob.bank.conta.adapters.AccountAdapter
import com.bob.bank.conta.adapters.`in`.controllers.request.AccountRequest
import com.bob.bank.conta.adapters.utils.AccountUtils
import com.bob.bank.conta.application.core.domain.Account
import com.bob.bank.conta.application.core.domain.CheckingAccount
import com.bob.bank.conta.application.core.domain.SavingsAccount
import com.bob.bank.conta.application.ports.`in`.CreateAccountInputPort

class CreateAccountUseCase(
    private val accountAdapter: AccountAdapter,
    private val accountUtils: AccountUtils
): CreateAccountInputPort{

    override fun createAccount(customerId: String): List<Account> {
        val checkingAccount = CheckingAccount(
            customerId = customerId,
            accountNumber = accountUtils.generateAccountNumber()
            )
        val savingsAccount = SavingsAccount(
            customerId = customerId,
            accountNumber = "10" + accountUtils.generateAccountNumber()
        )
        val accounts = listOf(checkingAccount, savingsAccount)

        try {
            accountAdapter.saveAccount(accounts)
        } catch (ex: Exception) {
            throw RuntimeException("Não foi possivel criar a conta!")
        }

        return accounts
    }
}