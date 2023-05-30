package com.pp.laborator
import kotlinx.coroutines.*
import kotlin.system.*
import java.io.File
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock


//definim massiveRun, cu parametrul action()
suspend fun CoroutineScope.massiveRun(action: suspend () -> Unit) {
    val n = 100                                         	//numar corutine
    val k = 1000                                        	//numar de apeluri ale action()
    val time = measureTimeMillis {
        val jobs = List(n) {                       		//lista de corutine
            launch { repeat(k) { action() } }           	//repeta de k ori apelul action, care incrementa counter, in timpul fiecarei corutine
            //action utilizeaza coutnerMutex pentru a bloca si debloca accesul la conter
        }
        jobs.forEach { it.join() }                      	//asteptam sa se finalizeze toate corutinele
    }
    println("S-au efectuat ${n * k} operații în $time ms")
}

//rulam in mod concurent ultilizand mtContext, maxim 2 fire de executie
val mtContext = newFixedThreadPoolContext(2, "mtPool")
var counter = 0
val counterMutex = Mutex()  //pentru a nu accesa variabila counter in acelasi timp (blocheaza/deblocheaza accesul la resurse partajate)

fun main() = runBlocking<Unit> {
    CoroutineScope(mtContext).massiveRun {
        counterMutex.withLock {
            counter++
        }
    }
    println("Numărator = $counter")

    val values = generateValues() // Obțineți lista de obiecte ADT
    writeValuesToFileConcurrently(values, "output.txt")
}


data class Value(val data: String)

fun generateValues(): List<Value> {
    val values = mutableListOf<Value>()
    for (i in 1..1000) {
        values.add(Value("Operatia $i"))
    }
    return values
}


fun writeValuesToFileConcurrently(values: List<Value>, filename: String) = runBlocking {
    val fileMutex = Mutex()

    coroutineScope {
        values.forEach { value ->
            launch {
                fileMutex.withLock {
                    File(filename).appendText("${value.data}\n")
                }
            }
        }
    }
}

