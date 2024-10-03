package com.bob.bank.conta.adapters.utils

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class AccountUtilsTest {

    private val accountUtils = AccountUtils()

    @Test
    fun `should generate account number with 5 digits`() {
        val accountNumber = accountUtils.generateAccountNumber()

        assertEquals(5, accountNumber.length)
    }

    @Test
    fun `should generate numeric account number`() {
        val accountNumber = accountUtils.generateAccountNumber()

        assertTrue(accountNumber.all { it.isDigit() })
    }

    @Test
    fun `should generate different account numbers`() {
        val accountNumber1 = accountUtils.generateAccountNumber()
        val accountNumber2 = accountUtils.generateAccountNumber()

        assertNotEquals(accountNumber1, accountNumber2)
    }

}