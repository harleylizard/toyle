package com.harleylizard.toyle.tree

import com.harleylizard.toyle.tree.token.Tokens
import org.objectweb.asm.ClassWriter

class Function : Tree {

    override fun consume(tokens: Tokens) {
        TODO("Not yet implemented")
    }

    override fun asmify(tokens: Tokens, writer: ClassWriter, options: Options) {
        //val visitor = writer.visitMethod()

        //visitor.visitEnd()
    }
}