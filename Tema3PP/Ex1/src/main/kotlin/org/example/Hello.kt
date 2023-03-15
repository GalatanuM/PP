package org.example
import org.jsoup.Jsoup
class Item(val titlu: String, val link: String, val descriere: String, val dataPublicarii: String)

fun procesareURL(url: String):List<Item>
{
    val iteme = mutableListOf<Item>()
    val iaTot = Jsoup.connect(url).get()
    val toateItemele = iaTot.select("item")
    for (x in toateItemele)
    {
        val titlu = x.select("title").text()
        val link = x.select("link").text()
        val descriere = x.select("description").text()
        val dataPublicarii = x.select("pubDate").text()
        val aux = Item(titlu,link,descriere,dataPublicarii)
        iteme.add(aux)
    }
    return iteme
}

fun afisareIteme(iteme: List<Item>)
{
    iteme.forEach()
    {
        println("Titlu: " + it.titlu)
        println("Link: " + it.link)
        println()
    }
}
fun main(args: Array<String>)
{
    val url = "http://stiri.tvr.ro/rss/vremea.xml"
    val iteme = procesareURL(url)
    afisareIteme(iteme)
}

