package com.harleylizard.toyle.tree

import com.harleylizard.toyle.tree.token.NameToken
import org.objectweb.asm.ClassWriter
import java.nio.file.Path

interface Options {
    val version: Int

    fun getName(token: NameToken): String

    fun write(writer: ClassWriter, path: Path)
}