fun main() {
    val content1 = Content("Marco", "haha", "Carte 1", "Publicatia 1")
    val content2 = Content("Alin", "hehe", "Carte 2", "Publicatia 2")
    val content3 = Content("Ana", "hihi", "Carte 2", "Publicatia 1")

    val book1 = Book(content1, 10.99)
    val book2 = Book(content2, 12.99)
    val book3 = Book(content3, 6.99)

    val library = Library(mutableSetOf(book1,book2,book3))
    library.addBook(book1)
    library.addBook(book2)
    library.addBook(book3)

    val booksByAuthor = library.findAllByAuthor("Marco")
    println("Cartile lui Marco:")
    booksByAuthor.forEach { println(it.getName()) }
    println()

    val booksByName = library.findAllByName("Carte 2")
    println("Cartile numite Carte 2 scrise de:")
    booksByName.forEach { println(it.getAuthor()) }
    println()

    val booksByPublisher = library.findAllByPublisher("Publicatia 1")
    println("Cartile publicate de Publicatia 1:")
    booksByPublisher.forEach { println(it.getName()) }
    println()

    val libraryprinter = LibraryPrinter()

    println("Cartile lui Marco RAW:\n")
    libraryprinter.printBooksRaw(booksByAuthor)
    println()

    println("Cartile lui Marco in HTML:")
    libraryprinter.printHTML(booksByAuthor)
    println()

    println("Cartile lui Marco in JSON:")
    libraryprinter.printJSON(booksByAuthor)
    println()
}