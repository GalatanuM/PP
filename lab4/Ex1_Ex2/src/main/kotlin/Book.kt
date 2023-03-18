

open class Book(private var data: Content, private var author: String, private var text: String, private var name: String, private var publisher: String, private var price: Double): Content(author, text, name, publisher)
{
    fun laString():String
    {
        return data.toString()
    }

    override fun getAuthor(): String {
        return super.getAuthor()
    }

    override fun setAuthor(author: String)
    {
        super.setAuthor(author)
    }

    override fun getName(): String
    {
        return super.getName()
    }

    override fun setName(name: String)
    {
        super.setName(name)
    }

    override fun getText(): String
    {
        return super.getText()
    }

    override fun setText(text: String)
    {
        super.setText(text)
    }

    override fun getPublisher(): String
    {
        return super.getPublisher()
    }

    override fun setPublisher(publisher: String)
    {
        super.setPublisher(publisher)
    }

    fun setPrice(pret: Double)
    {
        if(pret>0)
            this.price=pret
        else
            println("Pret invalid!\n")
    }

    fun getPrice():Double
    {
        return this.price
    }
}