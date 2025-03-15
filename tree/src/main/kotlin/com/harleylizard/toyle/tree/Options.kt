package com.harleylizard.toyle.tree

import com.harleylizard.toyle.tree.token.NameToken
import com.harleylizard.toyle.tree.token.Token
import org.objectweb.asm.ClassWriter
import org.objectweb.asm.Type
import java.nio.file.Path

interface Options {
    val version: Int

    fun getName(token: NameToken): String

    fun getTypeResults(token: Token): List<TypeResult>

    fun getType(token: Token): Type

    fun write(writer: ClassWriter, path: Path)
}