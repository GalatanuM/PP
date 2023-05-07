package factory

class FactoryProducer {
    fun getFactory(choice: String): AbstractFactory {
        return when (choice) {
            "EliteFactory" -> EliteFactory()
            "HappyWorkerFactory" -> HappyWorkerFactory()
            else -> throw IllegalArgumentException("FactoryProducer: Nu se poate crea o fabrică pentru '$choice'.")
        }
    }
}
