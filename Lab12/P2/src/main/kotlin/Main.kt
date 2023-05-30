import java.text.SimpleDateFormat
import java.util.Date

fun String.toDate(formatter: SimpleDateFormat): Date? {
    return formatter.parse(this)
}

fun main()
{
    val dateString = "2023-05-29"
    val formatter = SimpleDateFormat("yyyy-MM-dd")
    val date = dateString.toDate(formatter)
    println(date) // Output: Mon May 29 00:00:00 EEST 2023
}