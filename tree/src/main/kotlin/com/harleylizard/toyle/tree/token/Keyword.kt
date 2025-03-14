package com.harleylizard.toyle.tree.token

enum class Keyword {
    PACKAGE,
    IMPORT,
    CLASS
    ;

    val asToken get() = KeywordToken(this)
}