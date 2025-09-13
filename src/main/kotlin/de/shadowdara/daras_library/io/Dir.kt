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

fun getCallerJarDirectory(): File {
    return try {
        // 1. Stacktrace analysieren, um die aufrufende Klasse zu finden (nicht die Library)
        val stackTrace = Thread.currentThread().stackTrace

        // Die ersten paar Elemente im StackTrace sind von JVM/Thread/Reflection/etc.
        // Wir suchen die erste Klasse, die NICHT aus dieser Library stammt
        val callingClassName = stackTrace
            .firstOrNull { element ->
                // Hier ausschließen, was zur Library gehört – z.B. dein Paketname oder Klassenname
                val className = element.className
                !className.startsWith("java.") &&
                        !className.startsWith("kotlin.") &&
                        !className.startsWith("scala.") &&
                        !className.contains("de.shadowdara.daras_library") // <- anpassen
            }?.className ?: throw IllegalStateException("Could not determine calling class")

        // 2. Klasse laden
        val callingClass = Class.forName(callingClassName)

        // 3. JAR-Pfad der aufrufenden Klasse extrahieren
        val uri = callingClass.protectionDomain.codeSource.location.toURI()
        File(uri).parentFile
    } catch (e: Exception) {
        throw RuntimeException("JAR Directory could not be discovered!", e)
    }
}
