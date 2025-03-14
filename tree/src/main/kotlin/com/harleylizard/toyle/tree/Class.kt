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
    }

    override fun asmify(tokens: Tokens, writer: ClassWriter, options: Options) {
        tokens.skip()
        val className = tokens.get<NameToken>()

        val access = Opcodes.ACC_PUBLIC or Opcodes.ACC_FINAL
        writer.visit(options.jvmVersion, access, "", null, "java/lang/Object", null)

        val visitor = writer.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null)
        visitor.visitCode()
        visitor.visitVarInsn(Opcodes.ALOAD, 0)
        visitor.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false)
        visitor.visitInsn(Opcodes.RETURN)
        visitor.visitEnd()

        writer.visitEnd()
    }
}