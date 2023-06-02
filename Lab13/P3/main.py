
def zip_func(*iterables):
    return map(lambda *args: args, *iterables)

if __name__ == '__main__':
    numbers = [1, 2, 3]
    letters = ['a', 'b', 'c']
    zipped = zip_func(numbers, letters)
    print(list(zipped))
