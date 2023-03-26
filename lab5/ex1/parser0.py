import os
import tkinter as tk
import pygubu


def prime(n):
    if n <= 1:
        return False
    for i in range(2, int(n/2)+1):
        if n % i == 0:
            return False
    return True

class Parser:
    ROOT_DIR = os.path.dirname(os.path.abspath(__file__))

    def __init__(self, master):
        self.master = master
        # 1: Create a builder
        self.builder = builder = pygubu.Builder()

        # 2: Load an ui file
        ui_path = os.path.join(self.ROOT_DIR, 'parser0.ui')
        builder.add_from_file(ui_path)

        # 3: Create the widget using a master as parent
        self.parser = builder.get_object('Parser', master)

        self.add_list_btn = self.builder.get_object('add_list_btn')
        self.filter_odd_btn = self.builder.get_object('filter_odd_btn')
        self.filter_primes_btn = self.builder.get_object('filter_primes_btn')

        self.integer_list_text = self.builder.get_object('integer_list_text')

        self.result_text = self.builder.get_object('result_text')

        self.add_list_btn['command'] = self.add_list
        self.filter_odd_btn['command'] = self.filter_odd
        self.filter_primes_btn['command'] = self.filter_primes

        builder.connect_callbacks(self)
        self.integer_list = None

    def add_list(self):
        result = self.integer_list_text.get("1.0", tk.END)
        result = result.strip().replace(' ', '')
        result = [int(item) for item in result.split(',')]
        self.integer_list = result
        self.result_text.delete("1.0", tk.END)
        self.result_text.insert(tk.END, result)

    def filter_odd(self):
        result = [int(i) for i in self.integer_list if i % 2 == 0]
        self.integer_list = result
        self.result_text.delete("1.0", tk.END)
        self.result_text.insert(tk.END, result)

    def filter_primes(self):
        result = [int(i) for i in self.integer_list if prime(i)]
        self.integer_list = result
        self.result_text.delete("1.0", tk.END)
        self.result_text.insert(tk.END, result)

if __name__ == '__main__':
    root = tk.Tk()
    root.title('Exemplul 1 cu Tkinter si PyGubu')
    app = Parser(root)
    root.mainloop()
