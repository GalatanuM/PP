
if __name__ == '__main__':
    n = int(input("Introduceti valoarea lui n: "))

    perfect_squares = (x for x in range(n + 1) if x % 2 == 0 and int(x ** 0.5) ** 2 == x)

    print(list(perfect_squares))
