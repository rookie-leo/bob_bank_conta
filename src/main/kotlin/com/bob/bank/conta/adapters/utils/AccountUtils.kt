package com.bob.bank.conta.adapters.utils

import org.springframework.stereotype.Component
import kotlin.random.Random

@Component
class AccountUtils {

    fun generateAccountNumber(): String {
        return List(5) { Random.nextInt(0, 10) }.joinToString(separator = "")
    }

}
