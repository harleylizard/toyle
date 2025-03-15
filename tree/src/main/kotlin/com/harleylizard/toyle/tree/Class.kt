package com.harleylizard.toyle.tree

import com.harleylizard.toyle.tree.token.Keyword
import com.harleylizard.toyle.tree.token.NameToken
import com.harleylizard.toyle.tree.token.Tokens
import org.objectweb.asm.ClassWriter
import org.objectweb.asm.Opcodes

class Class : Tree {

    override fun consume(tokens: Tokens) {
        tokens.expect(Keyword.CLASS)
        tokens.expect(NameToken::class)

        tokens.expect(Keyword.OPEN_BRACKET)
        while (!tokens.`is`(Keyword.CLOSE_BRACKET)) {
            if (tokens.`is`(Keyword.FUNCTION)) {
                WrappedTree.toyleFunction.consume(tokens)
            }
        }
        tokens.expect(Keyword.CLOSE_BRACKET)
    }

    override fun asmify(tokens: Tokens, writer: ClassWriter, options: Options) {
        tokens.skip()
        val className = tokens.get<NameToken>()
        tokens.skip()

        val access = Opcodes.ACC_PUBLIC or Opcodes.ACC_FINAL
        writer.visit(options.version, access, options.getName(className), null, "java/lang/Object", null)

        val visitor = writer.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null)
        visitor.visitCode()
        visitor.visitVarInsn(Opcodes.ALOAD, 0)
        visitor.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false)
        visitor.visitInsn(Opcodes.RETURN)
        visitor.visitEnd()

        tokens.skip()
        while (!tokens.`is`(Keyword.CLOSE_BRACKET)) {
            if (tokens.`is`(Keyword.FUNCTION)) {
                WrappedTree.toyleFunction.asmify(tokens, writer, options)
            }
        }
        tokens.skip()

        writer.visitEnd()
    }
}