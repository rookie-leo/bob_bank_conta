package com.bob.bank.conta.config

import com.bob.bank.conta.adapters.AccountAdapter
import com.bob.bank.conta.application.core.usecases.CreateAccountUseCase
import com.bob.bank.conta.adapters.utils.AccountUtils
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CreateAccountConfig {

    @Bean
    fun createAccount(accountAdapter: AccountAdapter, accountUtils: AccountUtils): CreateAccountUseCase =
        CreateAccountUseCase(accountAdapter, accountUtils)
}