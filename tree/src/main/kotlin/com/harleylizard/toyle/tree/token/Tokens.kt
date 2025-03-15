package com.harleylizard.toyle.tree.token

import java.util.*
import kotlin.reflect.KClass

class Tokens private constructor(private val tokens: List<Token>) {
    val next get() = tokens[current + 1]

    val token get() = tokens[current]

    private var current = 0

    fun identity() {
        current = 0
    }

    fun `is`(other: Token) = tokens[current] == other

    fun `is`(keyword: Keyword) = `is`(keyword.asToken)

    fun <T : Token> `is`(type: KClass<T>) = tokens[current]::class == type

    fun indexOf(i: Int, token: Token): Int {
        for (j in i .. tokens.size) {
            if (tokens[j] == token) {
                return j
            }
        }
        return -1
    }

    fun skip(i: Int) {
        current += i
    }

    fun skip() {
        current++
    }

    fun <T : Token> get(): T {
        return tokens[current] as T
    }

    fun anyOf(list: List<Token>) = predicateOf {
        var result = false
        for (other in list) {
            if (`is`(other)) {
                result = true
                break
            }
        }
        result
    }

    fun anyOf(vararg keywords: Keyword) = anyOf(keywords.map { it.asToken })

    fun <T : Token> expect(type: KClass<T>) {
        if (!`is`(type)) {
            error()
        }
        skip()
    }

    fun expect(other: Token) {
        if (!`is`(other)) {
            error()
        }
        skip()
    }

    fun expect(keyword: Keyword) {
        expect(keyword.asToken)
    }

    fun error() {
        throw RuntimeException("Wrong token at index $current.")
    }

    fun predicateOf(predicate: (Tokens) -> Boolean) = TokenPredicate(this, predicate)

    companion object {
        val empty = Tokens(emptyList())

        fun tokensOf(list: List<Token>) = Tokens(Collections.unmodifiableList(list))
    }
}