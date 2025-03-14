package com.harleylizard.toyle.tree

import com.harleylizard.toyle.tree.token.Tokens
import org.objectweb.asm.ClassWriter

sealed interface Tree {

    fun consume(tokens: Tokens)

    fun asmify(tokens: Tokens, writer: ClassWriter, options: Options)
}