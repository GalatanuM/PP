import java.io.File
import java.util.*


fun procesareText (fisier: String) : String
{
    var aux = fisier

    println("Optiuni:\n" +
                    "1. Eliminarea spatiilor multiple\n" +
                    "2. Eliminarea salturilor la linie noua multiple in text\n" +
                    "3. Detectarea si eliminarea numarului de pagina\n" +
                    "4. Toate de mai sus ^^^\n" +
                    "Introduceti optiunea: ")

    var optiune = Scanner(System.`in`).nextLine().toInt()

    when(optiune)
    {
        1 -> aux=aux.replace(Regex("[ ]+")," ")
        2 -> aux=aux.replace(Regex("[\n]+"),"\n")
        3 -> aux=aux.replace(Regex("[0-9]+"),"")
        4 -> {
            aux=aux.replace(Regex("[0-9]+"),"")
            aux=aux.replace(Regex("[ ]+")," ")
            aux=aux.replace(Regex("[\n]+"),"\n")
        }
    }
    return aux
}
fun main()
{

    var file= File("ebook.txt")
    val outputFile = File("output.txt")
    outputFile.writeText("")
    var text= file.readText()

    var aux = procesareText(text)
    outputFile.appendText(aux)
}