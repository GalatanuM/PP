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

fun main() {
    val number = 17
    println(number.isPrime())
}
