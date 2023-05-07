class Originator(private var message: String) : Observable() {
    private val savedStates = mutableListOf<Memento>()

    fun saveToMemento(): Memento {
        return Memento(message)
    }

    fun restoreFromMemento(memento: Memento) {
        setMessage(memento.getState())
    }

    fun setMessage(message: String) {
        this.message = message
        notifyAll()
    }

    fun getSavedStates(): List<Memento> {
        return savedStates
    }

    fun addSavedState(memento: Memento) {
        savedStates.add(memento)
    }

    fun getMessage(): String {
        return this.message
    }
}
