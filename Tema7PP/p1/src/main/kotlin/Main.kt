import java.io.BufferedReader
import java.io.FileReader
import java.text.SimpleDateFormat
import java.util.*

data class HistoryLogRecord(val timestamp: Long, val commandLine: String) : Comparable<HistoryLogRecord> {
    override fun compareTo(other: HistoryLogRecord): Int = timestamp.compareTo(other.timestamp)
}

fun main() {
    val historyLogs = readHistoryLogs("/var/log/apt/history.log", 50)
    val hashMap = createHashMap(historyLogs)

    // Exemplu de utilizare a metodei generice de căutare și înlocuire
    val searchedRecord = HistoryLogRecord(1234567890L, "apt-get install package")
    val replacementRecord = HistoryLogRecord(9876543210L, "apt update")

    replaceRecord(searchedRecord, replacementRecord, hashMap)

    // Exemplu de utilizare a metodei generice de calcul al maximului
    val maxRecord = findMax(historyLogs[0], historyLogs[1])
    println("Max record: ${maxRecord.timestamp}, ${maxRecord.commandLine}")
}

private fun readHistoryLogs(filePath: String, numEntries: Int): List<HistoryLogRecord> {
    val historyLogs = mutableListOf<HistoryLogRecord>()

    BufferedReader(FileReader(filePath)).use { reader ->
        var line: String?
        var count = 0

        while (reader.readLine().also { line = it } != null) {
            if (line!!.startsWith("Start-Date:")) {
                val startDate = line!!.substring("Start-Date: ".length)

                if (++count > numEntries)
                    break

                val commandLine = reader.readLine().substring("Commandline: ".length)
                val requestedBy = reader.readLine().substring("Requested-By: ".length)
                reader.readLine() // Skip action line
                val endDate = reader.readLine().substring("End-Date: ".length)

                val format = SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.US)
                val parsedDate = format.parse(startDate)
                val timestamp = parsedDate.time

                val logRecord = HistoryLogRecord(timestamp, commandLine)
                historyLogs.add(logRecord)
            }
        }
    }

    return historyLogs
}

private fun createHashMap(historyLogs: List<HistoryLogRecord>): MutableMap<Long, HistoryLogRecord> {
    val hashMap = mutableMapOf<Long, HistoryLogRecord>()

    for (logRecord in historyLogs) {
        hashMap[logRecord.timestamp] = logRecord
    }

    return hashMap
}

private fun <T : Comparable<T>> findMax(obj1: T, obj2: T): T {
    return if (obj1 > obj2) obj1 else obj2
}

private fun <T : HistoryLogRecord> replaceRecord(
    searchedRecord: T,
    replacementRecord: T,
    hashMap: MutableMap<Long, in T>
) {
    for ((key, value) in hashMap.entries) {
        if (value == searchedRecord) {
            hashMap[key] = replacementRecord
        }
    }
}
