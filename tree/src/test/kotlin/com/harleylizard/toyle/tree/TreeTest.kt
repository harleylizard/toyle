package com.harleylizard.toyle.tree

import com.harleylizard.toyle.tree.token.*
import com.harleylizard.toyle.tree.token.NameToken.Companion.asToken
import org.junit.jupiter.api.Test
import org.objectweb.asm.ClassWriter
import org.objectweb.asm.Opcodes
import org.objectweb.asm.Type
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

class TreeTest {
    private val buildPath = Paths.get("build")

    @Test
    fun test() {
        val options = DefaultOptions()

        val writer = ClassWriter(ClassWriter.COMPUTE_MAXS or ClassWriter.COMPUTE_FRAMES)

        val tokens = Tokens.tokensOf(listOf(
            Keyword.CLASS.asToken,
            "Test".asToken,
            Keyword.OPEN_BRACKET.asToken,

            Keyword.FUNCTION.asToken,
            "example".asToken,

            Keyword.COLON.asToken,
            Keyword.NUMBER.asToken,

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

}