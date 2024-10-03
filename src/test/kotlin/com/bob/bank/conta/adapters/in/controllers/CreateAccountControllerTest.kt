package com.bob.bank.conta.adapters.`in`.controllers

import com.bob.bank.conta.adapters.`in`.controllers.request.AccountRequest
import com.bob.bank.conta.application.core.domain.Account
import com.bob.bank.conta.application.core.domain.CheckingAccount
import com.bob.bank.conta.application.core.domain.SavingsAccount
import com.bob.bank.conta.application.core.usecases.CreateAccountUseCase
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.math.BigDecimal

@WebMvcTest(CreateAccountController::class)
class CreateAccountControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @MockBean
    private lateinit var createAccountInputPort: CreateAccountUseCase

    @Test
    fun createAccount_should_return_created_status() {
        val request = getAccountRequest()
        val accounts = getCreatedAccounts()

        `when`(createAccountInputPort.createAccount(request)).thenReturn(accounts)

        mockMvc.perform(post("/api/v1/accounts/create")
            .contentType(APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isCreated)

        verify(createAccountInputPort, times(1)).createAccount(request)
    }

    @Test
    fun createAccount_should_return_badRequest_status() {
        val request = AccountRequest("", "")

        mockMvc.perform(post("/api/v1/accounts/create")
            .contentType(APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isBadRequest)

        verify(createAccountInputPort, times(0)).createAccount(request)
    }

    @Test
    fun createAccount_should_return_internalServerError_status() {
        val request = getAccountRequest()

        `when`(createAccountInputPort.createAccount(request)).thenThrow(RuntimeException("Não foi possivel criar a conta!"))

        mockMvc.perform(post("/api/v1/accounts/create")
            .contentType(APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isInternalServerError)

        verify(createAccountInputPort, times(1)).createAccount(request)
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
                "010123",
                BigDecimal("0.0")
            )
        )
    }

    private fun getAccountRequest(): AccountRequest {
        return AccountRequest("123456", "12345678912")
    }
}