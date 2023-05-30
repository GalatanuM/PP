import random
import threading
import multiprocessing
from concurrent.futures import ThreadPoolExecutor
import time


def is_prime(num):
    if num < 2:
        return False
    for i in range(2, int(num ** 0.5) + 1):
        if num % i == 0:
            return False
    return True

def countdown():
    numbers = random.sample(range(1, 11), 10)
    print("Numere:", numbers)
    primes = filter(is_prime, numbers)
    prime_list = list(primes)
    #convertim obiectul filtrat într-o listă
    print("Numere prime:", prime_list)

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
    start = time.time()
    ver_1()
    end = time.time()
    print("\nTimp executie pseudoparalelism cu GIL")
    print(end - start)

    start = time.time()
    ver_2()
    end = time.time()
    print("\nTimp executie secvential")
    print(end - start)

    start = time.time()
    ver_3()
    end = time.time()
    print("\nTimp executie paralela cu multiprocessing")
    print(end - start)

    start = time.time()
    ver_4()
    end = time.time()
    print("\nTimp executie paralela cu concurrent.futures")
    print(end - start)