//utilizam File din Java.io pentru a deschide fisierul text
import java.io.File

fun GetUniqueWordCount(all_words : List<String>) : MutableMap<String, Int> {
    //functia pentru calculul cuvintelor unice
    var result = mutableMapOf<String, Int>()
    /* alternativ
    * for (x in all_words) {
            if(!result.containsKey(x))
            *   result.put(x,1)
            else
                result[x] = result[x]!! + 1;
        }
    * */
    all_words.forEach {
            if(!result.containsKey(it))
                result.put(it,1)
            else
                result[it] = result[it]!! + 1;
        }
    return result
}

fun GetUniqueCharCount(all_chars : List<String>) : MutableMap<Char, Int> {
    //functia pentru calculul caracterelor unice
    var result = mutableMapOf<Char, Int>()
    all_chars.forEach {
        for (c in it) {
            if(!result.containsKey(c))
                result.put(c,1)
            else
                result[c] = result[c]!! + 1;
        }
    }
    return result
}

fun SortByHitCount(items : MutableMap<Char, Int>, how: Boolean) : MutableMap<Int, Char>{
    //functia de sortare a caracterelor, dupa valoare (frecventa), atat crescator cat si descrescator, in functie de how
    var result = mutableMapOf<Int, Char>()

    if(how==true)
    {
        val itemsSortat = items.entries.sortedByDescending { it.value }
            .associate { it.key to it.value }
            .toMutableMap()
        result = itemsSortat.entries.associateBy({ it.value }, { it.key })
            .toMutableMap()
    }
    else
    {
        val itemsSortat = items.entries.sortedBy { it.value }
            .associate { it.key to it.value }
            .toMutableMap()
        result = itemsSortat.entries.associateBy({ it.value }, { it.key })
            .toMutableMap()
    }

    return result
}

//functia main()
fun main(args : Array<String>){
    //citim liniile din fisier
    val lines = File("Fisier.txt").reader().readText()
    //construim un array de cuvinte, seprand prin spatiu
    val words = lines.split(" ")

    //eliminam semnele de punctuatie de pe marginile cuvintelor
    val trim_words = mutableListOf<String>()
    words.forEach {
        val filter = it.trim(',','.','"','?', '!',';')
        trim_words += filter.lowercase()
        print(filter + " ")
    }
    println("\n")

    //construim o lista cu toate caracterele folosite 'A..Z'
    val chars = mutableListOf<String>()
    trim_words.forEach {
        for (c in it){
            if (c in 'a'..'z' || c in 'A'..'Z') {
                chars += c.uppercaseChar().toString()
                print(c.uppercaseChar())
            }
        }
    }
    println("\n")

    //Pentru constructia histogramelor, R foloseste un mecanism prin care asociaza caracterelor unice, numarul total de aparitii (frecventa)
    // 1. Construiti in Kotlin acelasi mecanism de masurare a frecventei elementelor unice si afisati cuvintele unice din trim_words
    // 2. Construiti in Kotlin acelasi mecanism de masurare a frecventei elementelor unice si afisati caracterele unice din chars
    // 3. Pentru frecventele caracterelor unice caclulate anterior si
    //      A. Afisati perechile (frecventa -> Caracter) sortate crescator si descrescator
    //      B. afisati graficele variatiei de frecventa sortate anterior crescator si descrescator si concatenati-le intr-un grafic de puncte

    //construim histograma pentru cuvinte
    //RHistogram.BuildHistogram(trim_words.toTypedArray(), "Words", true)
    //construim histograma pentru caractere
    //RHistogram.BuildHistogram(chars.toTypedArray(), "Chars", true)
    //construim histograma pentru frecventa caracterelor

    println("Frecventa cuvintelor: ")
    for (x in GetUniqueWordCount(trim_words)) { println(x.key + "-" +x.value) }
    println("Frecventa literelor: ")
    for (x in GetUniqueCharCount(chars)) { println(x.key + "-" +x.value) }
    println("Frecventa literelor in ordine crescatoare: ")
    for (x in SortByHitCount(GetUniqueCharCount(chars),false)) { println(" ${x.key} - ${x.value} ") }
    println("Frecventa literelor in ordine descrescatoare: ")
    for (x in SortByHitCount(GetUniqueCharCount(chars),true)) { println(" ${x.key} - ${x.value} ") }

    RHistogram.BuildHistogram( (SortByHitCount (GetUniqueCharCount(chars),false).keys.toTypedArray().plus(SortByHitCount(GetUniqueCharCount(chars),true).keys.toTypedArray()) ),"Chars1", false)
}
