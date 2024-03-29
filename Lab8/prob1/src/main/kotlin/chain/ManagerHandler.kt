package chain

class ManagerHandler(private var next1: Handler? = null, private var next2: Handler? = null) : Handler {
    override fun handleRequest(forwardDirection: String, messageToBeProcessed: String)
    {
        if(messageToBeProcessed[0] == '3')
        {
            println("The Manager intercepted the message: $messageToBeProcessed")
        }
        else
        {
            when(forwardDirection)
            {
                "up" -> next1?.handleRequest(forwardDirection, messageToBeProcessed)
                "down" -> next2?.handleRequest(forwardDirection, messageToBeProcessed)
                else -> throw IllegalArgumentException("Invalid factory type: $forwardDirection")
            }
        }
    }
    override fun setHandlers(handler1: Handler?, handler2: Handler?)
    {
        next1 = handler1
        next2 = handler2
    }
}
