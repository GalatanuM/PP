

open class Book(private var data: Content, private var price: Double): Content(data.getAuthor(), data.getText(), data.getName(), data.getPublisher())
{
    fun laString():String
    {
        return data.toString()
    }

    override fun getAuthor(): String
    {
        return super.getAuthor()
    }

    override fun setAuthor(autor: String)
    {
        super.setAuthor(autor)
    }

    override fun getName(): String
    {
        return super.getName()
    }

    override fun setName(nume: String)
    {
        super.setName(nume)
    }

    override fun getText(): String
    {
        return super.getText()
    }

    override fun setText(scris: String)
    {
        super.setText(scris)
    }

    override fun getPublisher(): String
    {
        return super.getPublisher()
    }

    override fun setPublisher(publicatie: String)
    {
        super.setPublisher(publicatie)
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