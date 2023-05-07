import chain.CEOHandler
import factory.FactoryProducer

fun main() {
    // se creeaza 1xFactoryProducer, 1xEliteFactory, 1xHappyWorkerFactory
    //...

    val factoryProducer = FactoryProducer()
    val eliteFactory = factoryProducer.getFactory("EliteFactory")
    val happyWorkerFactory = factoryProducer.getFactory("HappyWorkerFactory")

    // crearea instantelor (prin intermediul celor 2 fabrici):
    // 2xCEOHandler, 2xExecutiveHandler, 2xManagerHandler, 2xHappyWorkerHandler
    //...

    val CEOHandler1 = eliteFactory.getHandler("CEO")
    val CEOHandler2 = eliteFactory.getHandler("CEO")

    val ManagerHandler1 = eliteFactory.getHandler("MANAGER")
    val ManagerHandler2 = eliteFactory.getHandler("MANAGER")

    val ExecutiveHandler1 = eliteFactory.getHandler("EXECUTIVE")
    val ExecutiveHandler2 = eliteFactory.getHandler("EXECUTIVE")

    val HappyWorkerHandler1 = happyWorkerFactory.getHandler("HAPPY_WORKER")
    val HappyWorkerHandler2 = happyWorkerFactory.getHandler("HAPPY_WORKER")

    // se construieste lantul (se verifica intai diagrama de obiecte si se realizeaza legaturile)
    //...

    CEOHandler1.setHandlers(ExecutiveHandler1, CEOHandler2)
    ExecutiveHandler1.setHandlers(ManagerHandler1, ExecutiveHandler2)
    ManagerHandler1.setHandlers(HappyWorkerHandler1, ManagerHandler2)
    HappyWorkerHandler1.setHandlers(null, HappyWorkerHandler2)

    CEOHandler2.setHandlers(CEOHandler1, ExecutiveHandler2)
    ExecutiveHandler2.setHandlers(ExecutiveHandler1, ManagerHandler2)
    ManagerHandler2.setHandlers(ManagerHandler1, HappyWorkerHandler2)
    HappyWorkerHandler2.setHandlers(HappyWorkerHandler1,null)

    CEOHandler1.handleRequest("down","2:Mesaj menit pentru Executiv ")
    ExecutiveHandler2.handleRequest("down","3:Mesaj menit pentru Manager")
    ManagerHandler2.handleRequest("down","1:Mesaj menit pentru CEO")
    HappyWorkerHandler1.handleRequest("up","4:Mesaj menit pentru HappyWorker")
}
