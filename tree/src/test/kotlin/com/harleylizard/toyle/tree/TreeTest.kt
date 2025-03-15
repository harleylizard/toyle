package com.harleylizard.toyle.tree

import com.harleylizard.toyle.tree.token.Keyword
import com.harleylizard.toyle.tree.token.NameToken
import com.harleylizard.toyle.tree.token.NameToken.Companion.asToken
import com.harleylizard.toyle.tree.token.Tokens
import org.junit.jupiter.api.Test
import org.objectweb.asm.ClassWriter
import org.objectweb.asm.Opcodes
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

class TreeTest {
    private val buildPath = Paths.get("build")

    @Test
    fun test() {
        val options = TestOptions()

        val writer = ClassWriter(ClassWriter.COMPUTE_MAXS or ClassWriter.COMPUTE_FRAMES)

        val tokens = Tokens.tokensOf(listOf(
            Keyword.CLASS.asToken,
            "Test".asToken,
            Keyword.OPEN_BRACKET.asToken,

            Keyword.FUNCTION.asToken,
            "example".asToken,

            Keyword.MINUS.asToken,
            Keyword.RIGHT_ARROW.asToken,
            Keyword.VOID.asToken,

            Keyword.OPEN_BRACKET.asToken,
            Keyword.CLOSE_BRACKET.asToken,

            Keyword.CLOSE_BRACKET.asToken,
        ))

        val tree = WrappedTree.toyleClass
        tree.consume(tokens)

        tokens.identity()
        tree.asmify(tokens, writer, options)

        if (!Files.isDirectory(buildPath)) {
            Files.createDirectories(buildPath)
        }
        options.write(writer, buildPath.resolve("Test.class"))
    }

    class TestOptions : Options {
        override val version = Opcodes.V23

        override fun getName(token: NameToken) = token.asString

        override fun write(writer: ClassWriter, path: Path) {
            Files.newOutputStream(path).use {
                it.write(writer.toByteArray())
                it.flush()
            }
        }
    }
}