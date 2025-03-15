package com.harleylizard.toyle.tree

import com.harleylizard.toyle.tree.token.Token

class TypeResult private constructor(val token: Token) {

    companion object {

        fun resultOf(token: Token) = TypeResult(token)
    }
}