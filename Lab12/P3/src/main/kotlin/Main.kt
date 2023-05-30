fun <K, V> Map<K, V>.invert(): Map<V, K> {
    return this.map { it.value to it.key }.toMap()
}
fun main() {
// Exemplu de utilizare:
    val map = mapOf(1 to "abc", 2 to "def", 3 to "ghi")
    val invertedMap = map.invert()
    println(invertedMap) // Output: {abc=1, def=2, ghi=3}
}