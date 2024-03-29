package chain

class CEOHandler(var next1: Handler? = null, var next2: Handler? = null): Handler
{
    override fun handleRequest(forwardDirection: String, messageToBeProcessed: String)
    {
        if(messageToBeProcessed[0] == '1')
        {
            println("The CEO intercepted the message: $messageToBeProcessed")
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
