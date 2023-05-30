import subprocess

command = input("Introduceți comanda cu pipeline-uri: ")

commands = command.split("|")
input_data = None

for cmd in commands:
    cmd = cmd.strip()
    args = cmd.split()

    # Crearea procesului și specificarea inputului
    process = subprocess.run(args, input=input_data, capture_output=True, text=True)

    # Obținerea output-ului procesului curent
    output_data = process.stdout.strip()
    print(output_data)

    # Pregătirea pentru următorul proces
    input_data = output_data

