package com.pp.laborator
import kotlinx.coroutines.*

fun main() {

    val valori = listOf(10, 20, 30, 100)    //lista valori cacul suma, creeaza corutine

    //creeaza un context pentru corutine, programul nu se va termina
    //pana cand nu se termina toate corutinele
    runBlocking {
        val jobs = mutableListOf<Job>() //referinte catre corutine
        //folosim jobs pentru a apela join si a astepta ca toate corutinele sa se termine
        //inainte de a incheia executia

        repeat(valori.size) { i ->  //cu repeat iterez prin valori
            val n = valori[i]
            val job = launch {
                val s = suma(n)
                println("Suma pentru n=$n: $s")
            }
            jobs.add(job)
        }

        // Așteptăm până când toate corutinele se termină
        jobs.forEach { it.join() }
    }
}

suspend fun suma(n: Int): Int {
    var sum = 0
    for (i in 0..n) {
        sum += i
    }
    return sum
}