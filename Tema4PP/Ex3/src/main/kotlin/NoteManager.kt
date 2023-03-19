import java.time.LocalDateTime

class NoteManager {
    private val notes = mutableListOf<Note>()

    private fun loadNote(title: String): Note?
    {
        for (note in notes)
        {
            if (note.title == title)
            {
                return note
            }
        }
        return null
    }

    fun viewNotes() {
        var k = 0
        for (note in notes) {
            println(note.title)
            k++
        }
        if(k==0) println("Nu exista notite")
        println()
    }

    fun showNote(nota: String)
    {
        for (x in notes)
        {
            if (x.title == nota)
            {
                val note = loadNote(nota)
                if (note != null)
                {
                    print("Titlu: ")
                    println(note.title)
                    print("Autor: ")
                    println(note.createdBy.username)
                    print("Data de creatie: ")
                    println(note.createdDate)
                    println("Continut:")
                    println(note.content)
                    println()
                }
                else
                {
                    println("Nu exista notita.")
                    println()
                }
            }
        }
    }

    fun createNote(title: String, content: String, createdBy: User) {
        val note = Note(title,content,createdBy,LocalDateTime.now())
        notes.add(note)
        println("Notita creata")
        println()
    }

    fun deleteNote(title: String) {
        val note = loadNote(title)
        if (note != null) {
            notes.remove(note)
            println("Notita stearsa")
        }
        else
        {
            println("Notita nu exista")
        }
    }
}
