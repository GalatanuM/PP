import threading
import multiprocessing
from concurrent.futures import ThreadPoolExecutor
import time
import random


def prime(numere):
    if numere < 2:
        return False
    for i in range(2, int(numere ** 0.5) + 1):
        if numere % i == 0:
            return False
    return True

def increment_segment(segment):
    for i in range(len(segment)):
        segment[i] += 1

def patrate_perfecte(numere):
    patrate = []
    for i in numere:
        patrate.append(i ** 2)
    return patrate

#utilizez threaduri pt a procesa segementele de numere
#impart setul de numere in segmente egale, pornesc un thread pt fiecare segment
#fiecare segment va apela functia process_segment
def ver_1(numere):
    segm_size = len(numere) // 2
    segm = [numere[i:i + segm_size] for i in range(0, len(numere), segm_size)]

    threads = []
    for segment in segm:
        thread = threading.Thread(target=process_segment, args=(segment,))
        thread.start()
        threads.append(thread)

    for thread in threads:
        thread.join()


#impart secvential, setul de nr e impartit in segmente, dar functia e apelata direct pe fiecare segment
def ver_2(numere):
    segm_size = len(numere) // 2
    segm = [numere[i:i + segm_size] for i in range(0, len(numere), segm_size)]

    for segment in segm:
        process_segment(segment)

#utilizez procese multiproceor pt a procesa in paralel
#impart setul in segmente, pornesc un proces pt fiecare segment
#fiecare proces ruleaza in spatiul sau de lucru
def ver_3(numere):
    segm_size = len(numere) // 2    #dimensiunea fiecare segment
    #utilizez for pt a itera prin indicii se start ai segmentelor
    #range de la indexul 0 la sf listei, cu pasul 2
    segm = [numere[i:i + segm_size] for i in range(0, len(numere), segm_size)]  #imparte lista de numere in segemnte

    processes = []
    for segment in segm:
        process = multiprocessing.Process(target=process_segment, args=(segment,))
        process.start()
        processes.append(process)

    for process in processes:
        process.join()


#utilizez ThreadPoolExecutor, creez un tip pool de threaduri si trimit fiecare segment
#pentru a fi procesat de executor (cu submit)
#executorul se va ocupa de asignarea segmentelor la threadurile disponibile
def ver_4(numere):
    segm_size = len(numere) // 2
    for i in range(0, len(numere), segm_size):
        segm = numere[i:i + segm_size]
    with ThreadPoolExecutor(max_workers=2) as executor:
        futures = [executor.submit(process_segment, segment) for segment in segm]


#primeste un proces si filtreaza numerele prime, apoi ridica la patrat toate numerele din segment
#le afisez
def process_segment(segment):
    #incrementarea valorilor din segment
    increment_segment(segment)

    #filtrare numere prime
    p = []
    for i in segment:
        if prime(i):
            p.append(i)

    #ridicare la patrat
    pp = patrate_perfecte(segment)

    print("Segment:", segment)
    print("Prime:", p)
    print("Patrate perfecte:", pp)
    print()


if __name__ == '__main__':
    numere = random.sample(range(1, 11), 10)
    print(numere)

    start = time.time()
    ver_1(numere)
    end = time.time()
    print("\n Timp executie pseudoparalelism cu GIL")
    print(end - start)

    start = time.time()
    ver_2(numere)
    end = time.time()
    print("\n Timp executie secvential")
    print(end - start)

    start = time.time()
    ver_3(numere)
    end = time.time()
    print("\n Timp executie paralela cu multiprocessing")
    print(end - start)

    start = time.time()
    ver_4(numere)
    end = time.time()
    print("\n Timp executie paralela cu concurrent.futures")
    print(end - start)