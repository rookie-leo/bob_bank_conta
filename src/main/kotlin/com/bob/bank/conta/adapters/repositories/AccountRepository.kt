package com.bob.bank.conta.adapters.repositories

import com.bob.bank.conta.adapters.repositories.entities.AccountEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountRepository: JpaRepository<AccountEntity, Long>  {
    fun findByCustomerId(customerId: String): List<AccountEntity>

}
