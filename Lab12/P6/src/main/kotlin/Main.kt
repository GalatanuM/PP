fun main() {
    print("Introdu un sir de caractere:")
    val input = readLine()
    val distinctChars = input?.asSequence()?.distinct()?.joinToString("")
    println(distinctChars) // Exemplu de output: "abc" pentru "aaaabbbcc"
}