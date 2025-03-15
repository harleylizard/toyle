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

        if (tokens.`is`(Keyword.COLON)) {
            tokens.skip()
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

        var returnType = listOf(TypeResult.resultOf(Keyword.VOID.asToken))
        if (tokens.`is`(Keyword.COLON)) {
            tokens.skip()

            returnType = options.getTypeResults(tokens.token)
            tokens.skip()
        }

        tokens.skip()

        for (typeResult in returnType) {
            val descriptor = options.getType(typeResult.token).descriptor

            val access = Opcodes.ACC_PUBLIC
            val visitor = writer.visitMethod(access, options.getName(functionName), "()$descriptor", null, null)

            visitor.visitEnd()
        }
        tokens.skip()
    }
}