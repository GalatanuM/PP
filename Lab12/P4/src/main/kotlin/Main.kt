import kotlin.properties.Delegates

fun Int.isPrime(): Boolean {
    if (this <= 1) {
        return false
    }
    for (i in 2 until this) {
        if (this % i == 0) {
            return false
        }
    }
    return true
}

var primeNumber: Int by Delegates.vetoable(2) { _, _, newValue ->
    newValue.isPrime()
}

fun main() {
    println(primeNumber) // Output: 2
    primeNumber = 5
    println(primeNumber) // Output: 5
    primeNumber = 10
    println(primeNumber) // Output: 5 (nu se schimbă pentru că 10 nu este prim)
}