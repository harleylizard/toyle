package com.harleylizard.toyle.tree.token

import java.util.Collections
import kotlin.reflect.KClass

class Tokens private constructor(private val tokens: List<Token>) {
    private var current = 0

    fun skip(i: Int) {
        current += i
    }

    fun skip() {
        current++
    }

    fun <T : Token> get(): T {
        return tokens[current] as T
    }

    fun <T : Token> expect(type: KClass<T>) {
        if (tokens[current]::class != type) {
            error()
        }
        skip()
    }

    fun expect(token: Token) {
        if (token != tokens[current]) {
            error()
        }
        skip()
    }

    fun expect(keyword: Keyword) {
        expect(keyword.asToken)
    }

    private fun error() {
        throw RuntimeException("Invalid token")
    }

    companion object {
        val empty = Tokens(emptyList())

        fun tokensOf(list: List<Token>) = Tokens(Collections.unmodifiableList(list))
    }
}