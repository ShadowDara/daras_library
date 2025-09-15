package de.shadowdara.daras_library

// Fortschrittsbalken ausgeben
fun printProgressBar(current: Int, total: Int, barLength: Int = 50) {
    val percent = current.toDouble() / total
    val filledLength = (barLength * percent).toInt()
    val bar = "█".repeat(filledLength) + "-".repeat(barLength - filledLength)
    print("\r[$bar] ${"%.1f".format(percent * 100)}%")
}
