package com.harleylizard.toyle.tree

import com.harleylizard.toyle.tree.token.Keyword
import com.harleylizard.toyle.tree.token.KeywordToken
import com.harleylizard.toyle.tree.token.NameToken
import com.harleylizard.toyle.tree.token.Token
import org.objectweb.asm.ClassWriter
import org.objectweb.asm.Opcodes
import org.objectweb.asm.Type
import java.nio.file.Files
import java.nio.file.Path

class DefaultOptions : Options {
    override val version = Opcodes.V23

    override fun getName(token: NameToken) = token.asString

    override fun getTypeResults(token: Token): List<TypeResult> {
        if (token is KeywordToken) {
            if (token.keyword == Keyword.NUMBER) {
                return listOf(
                    TypeResult.resultOf(Keyword.BYTE.asToken),
                    TypeResult.resultOf(Keyword.SHORT.asToken),
                    TypeResult.resultOf(Keyword.INT.asToken),
                    TypeResult.resultOf(Keyword.LONG.asToken),
                    TypeResult.resultOf(Keyword.FLOAT.asToken),
                    TypeResult.resultOf(Keyword.DOUBLE.asToken)
                )
            }
        }
        return listOf(TypeResult.resultOf(token))
    }

    override fun getType(token: Token): Type {
        if (token is KeywordToken) {
            return when (token.keyword) {
                Keyword.BYTE -> Type.BYTE_TYPE
                Keyword.SHORT -> Type.SHORT_TYPE
                Keyword.INT -> Type.INT_TYPE
                Keyword.LONG -> Type.LONG_TYPE
                Keyword.FLOAT -> Type.FLOAT_TYPE
                Keyword.DOUBLE -> Type.DOUBLE_TYPE
                Keyword.VOID -> Type.VOID_TYPE
                Keyword.BOOLEAN -> Type.BOOLEAN_TYPE
                else -> throw UnsupportedOperationException()
            }
        }
        throw UnsupportedOperationException()
    }

    override fun write(writer: ClassWriter, path: Path) {
        Files.newOutputStream(path).use {
            it.write(writer.toByteArray())
            it.flush()
        }
    }
}