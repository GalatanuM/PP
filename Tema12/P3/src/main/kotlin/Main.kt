import kotlin.math.*

fun calculateDistance(x1: Double, y1: Double, x2: Double, y2: Double): Double {
    val dx = x2 - x1
    val dy = y2 - y1
    return sqrt(dx * dx + dy * dy)
}

fun calculatePolygonPerimeter(points: List<Pair<Double, Double>>): Double {
    val n = points.size

    // Găsim punctul de referință (cel cu valoarea minimă a coordonatei y)
    val referencePoint = points.minByOrNull { it.second } ?: return 0.0

    // Sortăm punctele în funcție de unghiul lor față de punctul de referință
    val sortedPoints = points.sortedBy { point ->
        val dx = point.first - referencePoint.first
        val dy = point.second - referencePoint.second
        atan2(dy, dx)
    }

    // Eliminăm punctele coliniare
    val nonCollinearPoints = mutableListOf<Pair<Double, Double>>()
    for (i in 0 until n) {
        val prev = sortedPoints[(i - 1 + n) % n]
        val current = sortedPoints[i]
        val next = sortedPoints[(i + 1) % n]

        val crossProduct = (current.first - prev.first) * (next.second - current.second) -
                (current.second - prev.second) * (next.first - current.first)

        if (crossProduct != 0.0) {
            nonCollinearPoints.add(current)
        }
    }

    val perimeter = nonCollinearPoints.zipWithNext { p1, p2 ->
        calculateDistance(p1.first, p1.second, p2.first, p2.second)
    }.sum()

    // Adăugăm distanța dintre primul și ultimul punct (perechea care nu a fost creată de zipWithNext)
    val lastPoint = nonCollinearPoints.last()
    val firstPoint = nonCollinearPoints.first()
    val lastToFirstDistance = calculateDistance(lastPoint.first, lastPoint.second, firstPoint.first, firstPoint.second)
    return perimeter + lastToFirstDistance
}

fun main() {
    val n = readLine()?.toIntOrNull() ?: return
    val points = mutableListOf<Pair<Double, Double>>()

    repeat(n) {
        val (x, y) = readLine()?.split(" ")?.mapNotNull { it.toDoubleOrNull() } ?: return
        points.add(x to y)
    }

    val perimeter = calculatePolygonPerimeter(points)
    println(perimeter)
}
