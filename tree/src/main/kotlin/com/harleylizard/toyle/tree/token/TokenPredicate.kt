package com.harleylizard.toyle.tree.token

import kotlin.reflect.KClass

class TokenPredicate(private val tokens: Tokens, private val predicate: (Tokens) -> Boolean) {

    fun or(token: Token) = TokenPredicate(tokens) {
        var result = false
        if (predicate(tokens) || tokens.`is`(token)) {
            result = true
        }
        result
    }

    fun or(keyword: Keyword) = TokenPredicate(tokens) {
        var result = false
        if (predicate(tokens) || tokens.`is`(keyword)) {
            result = true
        }
        result
    }

    fun <T : Token> or(type: KClass<T>) = TokenPredicate(tokens) {
        var result = false
        if (predicate(tokens) || tokens.`is`(type)) {
            result = true
        }
        result
    }

    fun test() {
        if (!predicate(tokens)) {
            tokens.error()
        }
        tokens.skip()
    }

    private fun predicateOf(predicate: (Tokens) -> Boolean) = TokenPredicate(tokens, predicate)
}