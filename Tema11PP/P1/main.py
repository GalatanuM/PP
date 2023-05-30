import asyncio
from queue import Queue

async def calculate_sum(n, queue):
    sum_gauss = (n * (n + 1)) // 2
    print(f"Suma Gauss pentru {n} este: {sum_gauss}")
    await asyncio.sleep(1)
    queue.put(sum_gauss)

async def main():
    queue = Queue()
    coroutines = []
    values = [5, 10, 15, 20]  # Valorile lui n

    for value in values:
        coroutines.append(calculate_sum(value, queue))

    await asyncio.gather(*coroutines)

    while not queue.empty():
        result = queue.get()
        print(f"Rezultatul obținut din coadă: {result}")

if __name__ == '__main__':
    asyncio.run(main())
