from functional import seq

string = input("Introduceti un string: ")

result = seq(list(string)).distinct().to_list()

print(''.join(result))