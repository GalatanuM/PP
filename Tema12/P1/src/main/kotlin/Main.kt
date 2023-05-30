fun main() {
    val list = listOf(1, 21, 75, 39, 7, 2, 35, 3, 31, 7, 8)

    // Eliminarea oricărui număr < 5
    val filteredList = list.filter { it >= 5 }
    println(filteredList)

    // Gruparea numerelor în perechi
    val pairs = filteredList.zipWithNext().filterIndexed { index, _ -> index % 2 == 0 }
    println(pairs)

    // Multiplicarea numerelor din perechi
    val multipliedList = pairs.map { it.first * it.second }
    println(multipliedList)

    // Sumarea rezultatelor
    val sum = multipliedList.sum()
    println(sum)
}
