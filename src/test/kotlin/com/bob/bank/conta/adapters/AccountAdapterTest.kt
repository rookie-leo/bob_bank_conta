package com.bob.bank.conta.adapters

import com.bob.bank.conta.adapters.repositories.AccountRepository
import com.bob.bank.conta.adapters.repositories.entities.AccountEntity
import com.bob.bank.conta.application.core.domain.Account
import com.bob.bank.conta.application.core.domain.CheckingAccount
import com.bob.bank.conta.application.core.domain.SavingsAccount
import com.bob.bank.conta.application.core.exceptions.AccountAlreadyExistsException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito.*
import java.math.BigDecimal

class AccountAdapterTest {

    private var repository = mock(AccountRepository::class.java)
    private val adapter = AccountAdapter(repository)

    @Test
    fun should_save_account_success() {
        val accounts = generateAccount()

        `when`(repository.findByCustomerId(accounts[0].customerId)).thenReturn(listOf())
        `when`(repository.save(any())).thenReturn(any())

        adapter.saveAccount(accounts)

        verify(repository, times(2)).save(any())
        verify(repository, times(1)).findByCustomerId(anyString())
    }

    @Test
    fun should_not_save_account() {
        val accounts = generateAccount()

        `when`(repository.findByCustomerId(accounts[0].customerId)).thenReturn(listOf(AccountEntity()))

        assertThrows<AccountAlreadyExistsException> {
            adapter.saveAccount(accounts)
        }

        verify(repository, times(0)).save(any())
        verify(repository, times(1)).findByCustomerId(anyString())
    }

    private fun generateAccount(): List<Account> {
        return listOf(
            CheckingAccount(
                customerId = "1",
                accountNumber = "12345",
                accountBalance = BigDecimal("0.0")
            ),
            SavingsAccount(
                customerId = "1",
                accountNumber = "1012345",
                accountBalance = BigDecimal("0.0")
            )
        )
    }

}