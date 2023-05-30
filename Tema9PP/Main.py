import subprocess

class FileHandler:
    def __init__(self, successor=None):
        self.successor = successor

    def handle_file(self, file_content):
        if self.can_handle(file_content):
            self.process_file(file_content)
        elif self.successor:
            self.successor.handle_file(file_content)
        else:
            print("Nu s-a putut determina tipul de fișier.")

    def can_handle(self, file_content):
        raise NotImplementedError()

    def process_file(self, file_content):
        raise NotImplementedError()

class KotlinHandler(FileHandler):
    def can_handle(self, file_content):
        return "fun " in file_content or "when" in file_content

    def process_file(self, file_content):
        print("Tipul fișierului: Kotlin")
        # Executăm conținutul fișierului Kotlin
        # Utilizăm subprocess pentru a executa comanda și obține output-ul
        output = subprocess.check_output(["kotlin", "-e", file_content])
        print(output.decode())

class BashHandler(FileHandler):
    def can_handle(self, file_content):
        return file_content.startswith("#!/bin/bash")

    def process_file(self, file_content):
        print("Tipul fișierului: Bash")
        # Executăm conținutul fișierului Bash
        output = subprocess.check_output(["bash", "-c", file_content])
        print(output.decode())

class JavaHandler(FileHandler):
    def can_handle(self, file_content):
        return "public static void main" in file_content

    def process_file(self, file_content):
        print("Tipul fișierului: Java")
        # Executăm conținutul fișierului Java
        output = subprocess.check_output(["java", "Main", file_content])
        print(output.decode())

class PythonHandler(FileHandler):
    def can_handle(self, file_content):
        return "def " in file_content

    def process_file(self, file_content):
        print("Tipul fișierului: Python")
        # Executăm conținutul fișierului Python
        output = subprocess.check_output(["python3", "-c", file_content])
        print(output.decode())

def run():
    # Citim conținutul fișierului din fișierul extern
    with open("file.txt", "r") as file:
        file_content = file.read()

    kotlin_handler = KotlinHandler()
    python_handler = PythonHandler()
    bash_handler = BashHandler()
    java_handler = JavaHandler()

    kotlin_handler.successor = python_handler
    python_handler.successor = bash_handler
    bash_handler.successor = java_handler
    python_handler.handle_file(file_content)

run()