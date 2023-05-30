fun main() {
    val input = readLine()
    val compressedString = input?.runLengthEncode()
    println(compressedString)
}

fun CharSequence.runLengthEncode(): String {
    if (isEmpty()) return ""

    val result = StringBuilder()
    var count = 1
    var prevChar = this[0]

    for (i in 1 until length) {
        val currentChar = this[i]
        if (currentChar == prevChar) {
            count++
        } else {
            result.append(prevChar)
            result.append(count)
            count = 1
            prevChar = currentChar
        }
    }

    result.append(prevChar)
    result.append(count)

    return result.toString()
}
