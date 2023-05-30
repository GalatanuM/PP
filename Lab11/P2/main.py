from threading import Thread, Condition
import time

class Buffer:
    def __init__(self):
        self.elemente = []
        self.conditie = Condition()

    def consumator(self):
        self.conditie.acquire()
        #verifica daca bufferul e gol
        if len(self.elemente) == 0:
            self.conditie.wait()        #asteapta
            print('mesaj de la consumator: nu am nimic disponibil')
        self.elemente.pop()
        print('mesaj de la consumator : am utilizat un element')
        print('mesaj de la consumator : mai am disponibil', len(self.elemente), 'elemente')
        self.conditie.notify()
        self.conditie.release()

    def produce(self):
        self.conditie.acquire()
        #daca bufferul e plin
        if len(self.elemente) == 10:
            self.conditie.wait()        #asteapta
            print('mesaj de la producator : am disponibile', len(self.elemente), 'elemente')
            print('mesaj de la producator : am oprit productia')
        self.elemente.append(1)
        print('mesaj de la producator : am produs', len(self.elemente), 'elemente')
        self.conditie.notify()
        self.conditie.release()

#threadul Consumator apleaza metoda consuma()
#verifica daca buferul e gol, si asteapta
class Consumator(Thread):
    def __init__(self, buffer):
        Thread.__init__(self)
        self.buffer = buffer

    def run(self):
        for i in range(5):
            self.buffer.consumator()


#thread-ul Producator apleaza produce() pt a produce un elem si a-l adauga in buffer
#verifica daca bufferul e plin
class Producator(Thread):
    def __init__(self, buffer):
        Thread.__init__(self)
        self.buffer = buffer

    def run(self):
        for i in range(5):
            self.buffer.produce()


if __name__ == '__main__':
    buffer = Buffer()
    producator = Producator(buffer)
    consumator = Consumator(buffer)
    producator.start()
    consumator.start()
    producator.join()
    consumator.join()