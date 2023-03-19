open class LibraryPrinter
{
    fun printBooksRaw(carti: Set<Book>)
    {
        carti.forEach()
        {
            println("Autor: " + it.getAuthor())
            println("Text: " + it.getText())
            println("Nume: " + it.getName())
            println("Publicatie: " + it.getPublisher())
            println("Pret: " + it.getPrice())
            println()
        }
    }
    fun printHTML(books: Set<Book>) {
        println("<html>")
        println("\t<head><title>Carti</title></head>")
        println("\t<body>")
        println("\t\t<h1>Carti</h1>")
        println("\t\t<ul>")
        books.forEach {
            println("\t\t\t<li>${it.getName()} scrisa de ${it.getAuthor()}, publicata de ${it.getPublisher()} care costa ${it.getPrice()}</li>")
        }
        println("\t\t</ul>")
        println("\t</body>")
        println("</html>")
    }

    fun printJSON(books: Set<Book>) {
        val jsonBooks = mutableListOf<String>()
        books.forEach {
            jsonBooks.add(
                """
                {
                    "Nume": "${it.getName()}",
                    "Autor": "${it.getAuthor()}",
                    "Publicatie": "${it.getPublisher()}",
                    "Pret": ${it.getPrice()}
                }
                """.trimIndent()
                         )
        }
        println("[${jsonBooks.joinToString(",")}]")
    }
}