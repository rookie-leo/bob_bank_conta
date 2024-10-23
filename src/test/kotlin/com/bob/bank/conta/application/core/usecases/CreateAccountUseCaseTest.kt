package com.bob.bank.conta.application.core.usecases

import com.bob.bank.conta.adapters.AccountAdapter
import com.bob.bank.conta.adapters.`in`.controllers.request.AccountRequest
import com.bob.bank.conta.application.core.domain.Account
import com.bob.bank.conta.application.core.domain.CheckingAccount
import com.bob.bank.conta.application.core.domain.SavingsAccount
import com.bob.bank.conta.adapters.utils.AccountUtils
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito.*
import java.math.BigDecimal
import kotlin.test.assertNotNull

class CreateAccountUseCaseTest {

    private val accountAdapter = mock(AccountAdapter::class.java)
    private val accountUtils = mock(AccountUtils::class.java)
    private val createAccountUseCase = CreateAccountUseCase(accountAdapter, accountUtils)

    @Test
    fun should_create_account_success() {
        val request = getAccountRequest()
        val accounts = getCreatedAccounts()

        doNothing().`when`(accountAdapter).saveAccount(accounts)
        `when`(accountUtils.generateAccountNumber()).thenReturn("123")

        val accountsResponse = createAccountUseCase.createAccount(request.customerId)

        assertNotNull(accountsResponse)
        assertEquals(request.customerId, accountsResponse[0].customerId)
        assertEquals(request.customerId, accountsResponse[1].customerId)
        verify(accountAdapter, times(1)).saveAccount(accounts)
        verify(accountUtils, times(2)).generateAccountNumber()
    }

    private fun getAccountRequest(): AccountRequest {
        return AccountRequest("123456", "12345678912")
    }

    private fun getCreatedAccounts(): List<Account> {
        return listOf(
            CheckingAccount(
                "123456",
                "123",
                BigDecimal("0.0")
            ),
            SavingsAccount(
                "123456",
                "10123",
                BigDecimal("0.0")
            )
        )
    }
}