import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


// Definim clasa SyslogRecord pentru a stoca informatiile din fisierul syslog

class SyslogRecord(val timestamp: String, val hostname: String, val applicationName: String, val pid: String?, val logEntry: String)

val monthMap = mapOf(
    "1" to "Jan",
    "2" to "Feb",
    "3" to "Mar",
    "4" to "Apr",
    "5" to "May",
    "6" to "Jun",
    "7" to "Jul",
    "8" to "Aug",
    "9" to "Sep",
    "10" to "Oct",
    "11" to "Nov",
    "12" to "Dec"
                    )


fun main() {
    // Obtine timpul curent

    val currentTime = LocalDateTime.now().toString()
    val formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
    val dateObject = LocalDateTime.parse(currentTime, formatter1)

    val currentMonth = dateObject.monthValue
    val currentDay = dateObject.dayOfMonth // am facut rost de zi de tip d
    //println(currentDay)

    val currentHour = dateObject.hour
    //println(currentHour)

    val currentMinute = dateObject.minute

    val currentSecond = dateObject.second
    //println(currentSecond)

    val currentMonthWord = monthMap[currentMonth.toString()] //am facut rost de Apr
    //println(currentMonthWord)

    // Creeaza un StringBuilder pentru a stoca continutul fisierului syslog
    val syslogContent = StringBuilder()

    // Citeste continutul fisierului syslog in StringBuilder
    File("/var/log/syslog").forEachLine {
        syslogContent.appendLine(it)
    }

    // Imparte continutul fisierului syslog in linii si creeaza un obiect SyslogRecord pentru fiecare linie
    val syslogRecords = syslogContent.toString().trim().lines().map {
        val elements = it.split(" ", limit = 7)
        //println(elements)
        val timestamp = elements[0] + " " + elements[2] + " " + elements[3]
        //println(timestamp)
        val hostname = elements[4]
        val applicationName = elements[5].substringBefore("[")
        val pid = elements[5].substringAfter("[").substringBefore("]").takeIf { it.isNotEmpty() }
        val logEntry = elements[6]
        SyslogRecord(timestamp, hostname, applicationName, pid, logEntry)
    }

    // Retine toate aplicatiile din luna curenta

    val targetMonthRecords = syslogRecords.filter { it.timestamp.substring(0,3) == currentMonthWord }

    //targetMonthRecords.forEach { println(it.timestamp.substring(3,5))}

    // Retine toate aplicatiile din ziua curenta

    val targetDayRecords = targetMonthRecords.filter { it.timestamp.substring(4,5) == currentDay.toString() }

    //targetDayRecords.forEach { println(it.timestamp.substring(3,5))}

    if(currentMinute <= 30)
    { //trebuie sa luam si din ora trecuta si din ora curenta
        val targetHour1Records = targetDayRecords.filter { it.timestamp.substring(6,8).toInt() == currentHour   } //ora curenta
        val targetHour2Records = targetDayRecords.filter { it.timestamp.substring(6,8).toInt() == currentHour-1 } //ora trecuta
        val targetMin1Records = targetHour1Records.filter { it.timestamp.substring(9, 11).toInt() <= currentMinute }
        val targetMin2Records = targetHour2Records.filter { it.timestamp.substring(9, 11).toInt() >= currentMinute+30 }

        targetMin2Records.forEach { println(it.applicationName) }
        targetMin1Records.forEach { println(it.applicationName) }
    }
    else
    { //e trecut de si 30, luam doar din ora curenta

        // Retine toate aplicatiile din ora curenta

        val targetHourRecords = targetDayRecords.filter { it.timestamp.substring(6, 8).toInt() == currentHour }

        // Retine toate aplicatiile din ultimele 30 min

        val targetMinRecords = targetHourRecords.filter { it.timestamp.substring(9, 11).toInt() <= currentMinute }

        targetMinRecords.forEach { println(it.applicationName) }
    }
}
