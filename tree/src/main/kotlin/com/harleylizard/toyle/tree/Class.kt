package com.harleylizard.toyle.tree

import com.harleylizard.toyle.tree.token.NameToken
import com.harleylizard.toyle.tree.token.Tokens
import org.objectweb.asm.ClassWriter
import org.objectweb.asm.Opcodes

class Class : Tree {

    override fun consume(tokens: Tokens) {
    }

    override fun asmify(tokens: Tokens, writer: ClassWriter, options: Options) {
        tokens.skip()
        val className = tokens.get<NameToken>()

        val access = Opcodes.ACC_PUBLIC or Opcodes.ACC_FINAL
        writer.visit(options.jvmVersion, access, "", null, "java/lang/Object", null)
        writer.visitEnd()
    }
}