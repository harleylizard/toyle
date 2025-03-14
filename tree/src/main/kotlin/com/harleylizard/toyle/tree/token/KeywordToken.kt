package com.harleylizard.toyle.tree.token

class KeywordToken(private val keyword: Keyword) : Token {

    override fun equals(other: Any?): Boolean {
        if (other is KeywordToken) {
            return other.keyword == keyword
        }
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return keyword.hashCode()
    }
}