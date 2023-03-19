import java.time.LocalDateTime

class Note(
        val title: String,
        val content: String,
        val createdBy: User,
        val createdDate: LocalDateTime
          )