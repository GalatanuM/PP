import random
import threading
import multiprocessing
from concurrent.futures import ThreadPoolExecutor
import time

vector = []

def generate_random_vector(n):
    global vector
    #generarea vectorului cu nr random
    vector = [random.randint(1, 100) for i in range(n)]

def countdown():
    global vector
    n = len(vector)
    #sortarea vectorului
    for i in range(n):
        for j in range(0, n - i - 1):
            if vector[j] > vector[j + 1]:
                vector[j], vector[j + 1] = vector[j + 1], vector[j]

def ver_1():
    thread_1 = threading.Thread(target=countdown)
    thread_2 = threading.Thread(target=countdown)
    thread_1.start()
    thread_2.start()
    thread_1.join()
    thread_2.join()

def ver_2():
    countdown()
    countdown()

def ver_3():
    process_1 = multiprocessing.Process(target=countdown)
    process_2 = multiprocessing.Process(target=countdown)
    process_1.start()
    process_2.start()
    process_1.join()
    process_2.join()

def ver_4():
    with ThreadPoolExecutor(max_workers=2) as executor:
        future = executor.submit(countdown)
        future = executor.submit(countdown)

if __name__ == '__main__':
    n=1000;
    generate_random_vector(n)

    start = time.time()
    ver_1()
    end = time.time()
    print("\nTimp executie pseudoparalelism cu GIL")
    print(end - start)

    generate_random_vector(n)

    start = time.time()
    ver_2()
    end = time.time()
    print("\nTimp executie secvential")
    print(end - start)

    generate_random_vector(n)


    start = time.time()
    ver_3()
    end = time.time()
    print("\nTimp executie paralela cu multiprocessing")
    print(end - start)


    generate_random_vector(n)

    start = time.time()
    ver_4()
    end = time.time()
    print("\nTimp executie paralela cu concurrent.futures")
    print(end - start)
