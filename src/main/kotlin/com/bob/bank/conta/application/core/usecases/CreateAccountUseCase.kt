package com.bob.bank.conta.application.core.usecases

import com.bob.bank.conta.adapters.AccountAdapter
import com.bob.bank.conta.adapters.`in`.controllers.request.AccountRequest
import com.bob.bank.conta.application.core.domain.Account
import com.bob.bank.conta.application.core.domain.CheckingAccount
import com.bob.bank.conta.application.core.domain.SavingsAccount
import com.bob.bank.conta.application.ports.`in`.CreateAccountInputPort
import kotlin.random.Random

class CreateAccountUseCase(
    private val accountAdapter: AccountAdapter
): CreateAccountInputPort{

    override fun createAccount(accountRequest: AccountRequest): List<Account> {
        val checkingAccount = CheckingAccount(
            customerId = accountRequest.customerId,
            accountNumber = generateAccountNumber()
            )
        val savingsAccount = SavingsAccount(
            customerId = accountRequest.customerId,
            accountNumber = "10" + generateAccountNumber()
        )
        val accounts = listOf(checkingAccount, savingsAccount)

        try {
            accountAdapter.createAccount(accounts)
        } catch (ex: Exception) {
            throw RuntimeException("Não foi possivel criar a conta!")
        }

        return accounts
    }

    private fun generateAccountNumber(): String {
        val accountNumber = List(5) { Random.nextInt(0, 10) }

        return accountNumber.joinToString(separator = "")
    }
}