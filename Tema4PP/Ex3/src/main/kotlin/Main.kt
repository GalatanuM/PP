import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val noteManager = NoteManager()

    println("Aplicatie notite")

    print("User: ")

    val user = User(scanner.nextLine())

    var exit = false
    while (!exit)
    {
        println("Optiuni:")
        println("1. Listeaza notite")
        println("2. Incarca notita")
        println("3. Creaza notita")
        println("4. Sterge notita")
        println("5. Iesire")
        println()
        println("Alege o optiune:")
        val option = scanner.nextLine()
        //scanner.nextLine()
        when (option)
        {
            "1" -> noteManager.viewNotes()
            "2" -> {
                println("Titlul notitei:")
                val title = scanner.nextLine()
                noteManager.showNote(title)
            }
            "3" -> {
                println("Titlu:")
                val title = scanner.nextLine()
                println("Continut:")
                val content = scanner.nextLine()
                noteManager.createNote(title,content,user)
            }
            "4" -> {
                println("Titlu:")
                val title = scanner.nextLine()
                noteManager.deleteNote(title)
            }
            "5" -> {
                println("Iesire!")
                exit = true
            }
            else -> println("Optiune invalida.")
        }
    }
}