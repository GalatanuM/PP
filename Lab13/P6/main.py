from functional import seq

string = input("Introduceți un string: ")

result = (
    seq(list(string))
    .group_by(lambda x: x)
    .map(lambda x: x[0] + str(len(list(x[1]))))
    .to_list()
)

print(''.join(result))
