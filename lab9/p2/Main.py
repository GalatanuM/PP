import os

def file_filter(paths):
    for path in paths:
        if os.path.isfile(path):
            yield path

def extension_filter(paths, extension):
    for path in paths:
        if path.endswith(extension):
            yield path

def line_numbering(paths):
    for path in paths:
        with open(path, 'r') as file:
            lines = file.readlines()
            line_count = len(lines)
            yield path, line_count

def display_results(results):
    for path, line_count in results:
        print(f"{path}: {line_count} lines")

# Exemplu de utilizare

# Lista de path-uri
file_paths = [
    '/home/student/PP/lab9/p2/file1.txt',
    '/home/student/PP/lab9/p2/file2.txt',
    '/home/student/PP/lab9/p2/file3.jpg',
    '/home/student/PP/lab9/p2/file4.txt'
]

# Pipeline-ul de generatoare
filtered_files = file_filter(file_paths)
filtered_txt_files = extension_filter(filtered_files, '.txt')
numbered_lines = line_numbering(filtered_txt_files)

# Afișarea rezultatelor
display_results(numbered_lines)
