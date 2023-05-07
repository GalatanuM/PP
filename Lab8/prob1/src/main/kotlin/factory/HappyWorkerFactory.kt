package factory

import chain.HappyWorkerHandler
import chain.Handler

class HappyWorkerFactory : AbstractFactory() {
    override fun getHandler(handler: String): Handler {
        return when (handler) {
            "HAPPY_WORKER" -> HappyWorkerHandler()
            else -> throw IllegalArgumentException("HappyWorkerFactory: Nu se poate crea un handler pentru '$handler'.")
        }
    }
}
