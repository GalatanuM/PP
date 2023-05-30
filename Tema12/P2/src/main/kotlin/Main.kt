import java.io.File

fun caesarEncrypt(text: String, offset: Int): String {
    val result = StringBuilder()
    for (char in text) {
        if (char.isLetter()) {
            val base = if (char.isLowerCase()) 'a'.toInt() else 'A'.toInt()
            val encryptedChar = ((char.toInt() - base + offset) % 26 + base).toChar()
            result.append(encryptedChar)
        } else {
            result.append(char)
        }
    }
    return result.toString()
}

fun main() {
    val filename = "input.txt"
    val offset = 3
    val fileContent = File(filename).readText()
    val processedContent = fileContent.split(" ")
        .map { if (it.length in 4..7) caesarEncrypt(it, offset) else it }
        .joinToString(" ")
    println(processedContent)
}
