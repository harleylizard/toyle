package com.harleylizard.toyle.tree.token

class KeywordToken(private val keyword: Keyword) : Token {
    override val asString: String get() = throw UnsupportedOperationException()

    override fun equals(other: Any?): Boolean {
        return if (other is KeywordToken) other.keyword == keyword else super.equals(other)
    }

    override fun hashCode(): Int {
        return keyword.hashCode()
    }
}