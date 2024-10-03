package com.bob.bank.conta.adapters.repositories.entities

import com.bob.bank.conta.application.core.domain.Account
import jakarta.persistence.*
import java.math.BigDecimal

@Entity(name = "conta")
@Access(AccessType.FIELD)
open class AccountEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false)
    override val customerId: String = "",

    @Column(nullable = false, unique = true)
    override val accountNumber: String = "",

    @Column(nullable = false)
    override var accountBalance: BigDecimal = BigDecimal.ZERO
): Account()