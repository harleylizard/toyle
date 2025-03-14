package com.harleylizard.toyle.tree.token

class NameToken private constructor(private val name: String) : Token {
    override val asString = name


    companion object {
        val String.asToken get() = NameToken(this)

    }
}