fun main() {
    print("Introdu un numar (de ex 3):")
    val n = readLine()?.toIntOrNull() ?: 0
    val list = listOf(1, 2, 3)
    val replicatedList = list.flatMap { element -> List(n) { element } }
    println(replicatedList) // Exemplu de output: [1, 1, 1, 2, 2, 2, 3, 3, 3]
}