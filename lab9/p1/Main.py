import copy
import json
class HTMLFile:
    def __init__(self, title, author, paragraphs):
        self.title = title
        self.author = author
        self.paragraphs = paragraphs

    def generate_html(self):
        html = f"<h1>{self.title}</h1>\n"
        html += f"<p>Author: {self.author}</p>\n"
        for paragraph in self.paragraphs:
            html += f"<p>{paragraph}</p>\n"
        return html

class JSONFile:
    def __init__(self, title, author, paragraphs):
        self.title = title
        self.author = author
        self.paragraphs = paragraphs

    def generate_json(self):
        data = {
            "title": self.title,
            "author": self.author,
            "paragraphs": self.paragraphs
        }
        return json.dumps(data)

class TextFile:
    def __init__(self, title, author, paragraphs):
        self.title = title
        self.author = author
        self.paragraphs = paragraphs

    def generate_text(self):
        text = f"Title: {self.title}\n"
        text += f"Author: {self.author}\n\n"
        for paragraph in self.paragraphs:
            text += f"{paragraph}\n"
        return text

    def clone(self):
        return copy.copy(self)

class Article:
    def __init__(self, title, author, paragraphs):
        self.title = title
        self.author = author
        self.paragraphs = paragraphs

class Blog:
    def __init__(self, title, author, paragraphs):
        self.title = title
        self.author = author
        self.paragraphs = paragraphs

# Citirea datelor de la tastatură
title = input("Introduceți titlul lucrării: ")
author = input("Introduceți autorul lucrării: ")
num_paragraphs = int(input("Introduceți numărul de paragrafe: "))

paragraphs = []
for i in range(num_paragraphs):
    paragraph = input(f"Introduceți paragraful {i+1}: ")
    paragraphs.append(paragraph)

# Crearea obiectului specificat
file_type = input("Introduceți tipul de fișier (HTML, JSON, Text): ")

if file_type == "HTML":
    file = HTMLFile(title, author, paragraphs)
    generated_file = file.generate_html()
elif file_type == "JSON":
    file = JSONFile(title, author, paragraphs)
    generated_file = file.generate_json()
elif file_type == "Text":
    file = TextFile(title, author, paragraphs)
    generated_file = file.generate_text()

print(generated_file)
