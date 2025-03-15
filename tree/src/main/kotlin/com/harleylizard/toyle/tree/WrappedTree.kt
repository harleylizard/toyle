package com.harleylizard.toyle.tree

import com.harleylizard.toyle.tree.token.Tokens
import org.objectweb.asm.ClassWriter

class WrappedTree<T : Tree> private constructor(private val tree: T) : Tree {

    override fun consume(tokens: Tokens) {
        tree.consume(tokens)
    }

    override fun asmify(tokens: Tokens, writer: ClassWriter, options: Options) {
        tree.asmify(tokens, writer, options)
    }

    companion object {
        private val Tree.asWrappedTree get() = WrappedTree(this)

        val toyleClass = Class().asWrappedTree
        val toyleFunction = Function().asWrappedTree

    }
}