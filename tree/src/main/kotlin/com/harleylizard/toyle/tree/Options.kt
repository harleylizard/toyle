package com.harleylizard.toyle.tree

import com.harleylizard.toyle.tree.token.NameToken

interface Options {
    val version: Int

    fun getName(token: NameToken): String
}