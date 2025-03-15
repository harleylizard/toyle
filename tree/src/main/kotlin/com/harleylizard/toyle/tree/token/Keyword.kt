package com.harleylizard.toyle.tree.token

enum class Keyword {
    PACKAGE,
    IMPORT,
    CLASS,
    FUNCTION,
    OPEN_PARENTHESIS,
    CLOSE_PARENTHESIS,
    OPEN_BRACKET,
    CLOSE_BRACKET,
    OPEN_SQUARE_BRACKET,
    CLOSE_SQUARE_BRACKET,
    COLON,

    NUMBER,
    BYTE,
    SHORT,
    INT,
    LONG,
    FLOAT,
    DOUBLE,
    VOID,
    BOOLEAN
    ;
    val asToken get() = runtimeCache.computeIfAbsent(this) { KeywordToken(this) }

    companion object {
        private val runtimeCache = mutableMapOf<Keyword, KeywordToken>()

        val numbers = listOf(BYTE, SHORT, INT, LONG, FLOAT, DOUBLE)

    }
}