package com.harleylizard.toyle.tree.token

enum class Keyword {
    PACKAGE,
    IMPORT,
    CLASS
    ;

    val asToken get() = runtimeCache.computeIfAbsent(this) { KeywordToken(this) }

    companion object {
        private val runtimeCache = mutableMapOf<Keyword, KeywordToken>()

    }
}