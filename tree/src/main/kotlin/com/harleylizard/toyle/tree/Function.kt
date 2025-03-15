package com.harleylizard.toyle.tree

import com.harleylizard.toyle.tree.token.Keyword
import com.harleylizard.toyle.tree.token.NameToken
import com.harleylizard.toyle.tree.token.Tokens
import org.objectweb.asm.ClassWriter
import org.objectweb.asm.Opcodes

class Function : Tree {

    override fun consume(tokens: Tokens) {
        tokens.expect(Keyword.FUNCTION)
        tokens.expect(NameToken::class)

        if (tokens.`is`(Keyword.MINUS)) {
            tokens.skip()
            tokens.expect(Keyword.RIGHT_ARROW)
            tokens.anyOf(
                Keyword.NUMBER,
                Keyword.VOID,
                Keyword.BYTE,
                Keyword.SHORT,
                Keyword.INT,
                Keyword.LONG,
                Keyword.FLOAT,
                Keyword.DOUBLE,
                Keyword.BOOLEAN).or(NameToken::class).test()
        }

        tokens.expect(Keyword.OPEN_BRACKET)
        tokens.expect(Keyword.CLOSE_BRACKET)
    }

    override fun asmify(tokens: Tokens, writer: ClassWriter, options: Options) {
        tokens.skip()
        val functionName = tokens.get<NameToken>()
        tokens.skip()

        val returnType = "V"
        if (tokens.`is`(Keyword.MINUS)) {
            tokens.skip()
            tokens.skip()

            tokens.skip()
        }

        val access = Opcodes.ACC_PUBLIC
        val visitor = writer.visitMethod(access, options.getName(functionName), "()$returnType", null, null)

        tokens.skip()
        tokens.skip()

        visitor.visitEnd()
    }
}