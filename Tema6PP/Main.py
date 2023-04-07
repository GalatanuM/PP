import os


def detect_file_type(content):
    # Calculate the frequency of ASCII/UTF8 characters
    ascii_freq = sum(1 for c in content if c==9 or c==10 or c==13 or (32 <= c <= 127)) / len(content)
    non_ascii_freq = sum(1 for c in content if (0 <= c <= 8) or c == 11 or c == 12 or c == 14 or (15 <= c <= 31) or (128 <= c <= 255)) / len(content)

    # daca e ASCII/UTF8
    if ascii_freq > 0.9 and non_ascii_freq < 0.1:
        return 'text ASCII/UTF8'

    # daca e UNICODE/UTF16
    null_freq = sum(1 for c in content if c == 0) / len(content)
    if null_freq >= 0.3:
        return 'text UNICODE/UTF16'

    # frecventa caracterelor
    freq = [0] * 256
    for c in content:
        freq[c] += 1

    # binar?
    uniform_freq = sum(1 for f in freq if 0 < f < 10) / len(freq)
    if uniform_freq > 0.8:
        return 'binar'

    # niciunul???
    return None

if __name__ == "__main__":
    ROOT_DIR = os.path.dirname(os.path.abspath(__file__))
    listaASCII = []
    listaUNICODE = []
    listaBINAR = []
    listaNECUNOSCUTE = []
    for root, subdirs, files in os.walk(ROOT_DIR):
        for file in os.listdir(root):
            file_path = os.path.join(root, file)
            if os.path.isfile(file_path):
                # deschide fișierul spre acces binar
                f = open(file_path, 'rb')
                try:
                    # în content se va depune o listă de octeți
                    content = f.read()
                    tip = detect_file_type(content)
                    if tip == 'text ASCII/UTF8':
                        listaASCII.append(file_path)
                    elif tip == 'text UNICODE/UTF16':
                        listaUNICODE.append(file_path)
                    elif tip == 'binar':
                        listaBINAR.append(file_path)
                    else:
                        listaNECUNOSCUTE.append(file_path)
                finally:
                    f.close()
    print("listaASCII: ",listaASCII)

    print("listaUNICODE: ",listaUNICODE)
    print("listaBINAR: ",listaBINAR)
    print("listaNECUNOSCUTE: ",listaNECUNOSCUTE)