class SmallWordConsumer(private val originator: Originator) : Observer() {
    fun update() {
        originator.restoreFromMemento(originator.getSavedStates()[originator.getSavedStates().size % 10])
    }
}
