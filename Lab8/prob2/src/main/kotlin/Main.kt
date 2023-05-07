import java.io.File

fun main() {
    // Citirea textului din fișier
    val file = File("lorem_ipsum.txt")
    val text = file.readText()

    // Initializarea originatorului si caretaker-ului
    val originator = Originator("")
    val caretaker = Caretaker()

    // Divizarea textului in cuvinte
    val words = text.split("\\s+".toRegex())

    // Parcurgerea cuvintelor
    for ((index, word) in words.withIndex()) {
        // Salvarea starii curente in originator
        originator.setMessage(word)
        caretaker.addMemento(originator.saveToMemento())

        // Verificarea lungimii cuvantului si notificarea observatorilor corespunzatori
        if (word.length <= 7) {
            if ((index + 1) % 10 == 0) {
                // Restaurarea starii salvate anterior la fiecare 10 cuvinte mici
                val memento = caretaker.getSavedStates()[caretaker.getSavedStates().size % 10]
                originator.restoreFromMemento(memento)
            }

            // Notificarea SmallWordConsumer
            val smallWordConsumer = SmallWordConsumer(originator)
            smallWordConsumer.update(originator.getMessage())
        } else {
            if ((index + 1) % 7 == 0) {
                // Restaurarea starii salvate anterior la fiecare 7 cuvinte mari
                val memento = caretaker.getSavedStates()[caretaker.getSavedStates().size % 7]
                originator.restoreFromMemento(memento)
            }

            // Notificarea LargeWordConsumer
            val largeWordConsumer = LargeWordConsumer(originator)
            largeWordConsumer.update(originator.getMessage())
        }
    }
}
