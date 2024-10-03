package com.bob.bank.conta.adapters

import com.bob.bank.conta.adapters.repositories.AccountRepository
import com.bob.bank.conta.adapters.repositories.entities.AccountEntity
import com.bob.bank.conta.application.core.domain.Account
import com.bob.bank.conta.application.core.exceptions.AccountAlreadyExistsException
import com.bob.bank.conta.application.ports.out.CustomerOutPutPort
import org.springframework.stereotype.Service

@Service
class AccountAdapter(
    private var repository: AccountRepository
): CustomerOutPutPort {

    override fun saveAccount(accounts: List<Account>) {
        val entity: List<AccountEntity> = repository.findByCustomerId(accounts[0].customerId)
        if (entity.isEmpty()) accounts.forEach { repository.save(it.toDomain()) }
        else throw AccountAlreadyExistsException("Cliente já possui conta cadastrada!")
    }

}
