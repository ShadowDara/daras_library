package de.shadowdara.daras_library.io

import java.io.File
import java.net.URISyntaxException

fun getJarDirectory(): File {
    return try {
        val uri = {}.javaClass.protectionDomain.codeSource.location.toURI()
        File(uri).parentFile
    } catch (e: URISyntaxException) {
        throw RuntimeException("JAR Directory could not be discovered!", e)
    }
}
