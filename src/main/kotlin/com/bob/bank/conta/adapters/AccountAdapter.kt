package com.bob.bank.conta.adapters

import com.bob.bank.conta.adapters.repositories.AccountRepository
import com.bob.bank.conta.adapters.repositories.entities.AccountEntity
import com.bob.bank.conta.application.core.domain.Account
import com.bob.bank.conta.application.ports.out.CustomerOutPutPort
import org.springframework.stereotype.Service

@Service
class AccountAdapter(
    private var repository: AccountRepository
): CustomerOutPutPort {

    override fun createAccount(accounts: List<Account>) {
        val entity: List<AccountEntity> = repository.findByCustomerId(accounts.get(0).customerId)
        if (entity.isEmpty()) accounts.forEach { repository.save(it.toDomain()) }
        else throw RuntimeException("Cliente já possui conta cadastrada!")//TODO- implementar exceção customizada
    }

}
