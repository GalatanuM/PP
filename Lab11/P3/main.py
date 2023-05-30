import multiprocessing

class IntegerADT:
    def __init__(self, data):
        self.data = multiprocessing.Array('i', data)  # Array partajat pentru datele întregi
        self.lock = multiprocessing.Lock()  # Lock pentru sincronizare

    def multiply(self, alpha):
        with self.lock:
            for i in range(len(self.data)):
                self.data[i] *= alpha

    def sort(self):
        with self.lock:
            self.data[:] = sorted(self.data)

    def display(self):
        with self.lock:
            print(self.data[:])


if __name__ == '__main__':
    data = [5, 2, 8, 1, 9]

    # Crearea instanței ADT cu datele inițiale
    integer_adt = IntegerADT(data)

    # Crearea proceselor pentru fiecare etapă a procesării în pipeline
    processes = [
        multiprocessing.Process(target=integer_adt.multiply, args=(2,)),
        multiprocessing.Process(target=integer_adt.sort),
        multiprocessing.Process(target=integer_adt.display),
    ]

    # Pornirea proceselor
    for process in processes:
        process.start()

    # Așteptarea terminării proceselor
    for process in processes:
        process.join()
