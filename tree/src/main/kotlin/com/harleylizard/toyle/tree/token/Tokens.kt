package com.harleylizard.toyle.tree.token

class Tokens(private val tokens: List<Token>) {
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

    fun expect() {
        val token = tokens[current]
        skip()
    }
}