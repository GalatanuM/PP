from functools import partial
from itertools import starmap
def apply_function_starmap(iterator, k):
    f = lambda x, k: x * x + k
    result = list(starmap(f, zip(iterator, [k]*len(iterator))))
    return result

def apply_function_partial(iterator, k):
    f = lambda x, k: x * x + k
    partial_f = partial(starmap, f, zip(iterator, [k]*len(iterator)))
    result = list(partial_f())
    return result


if __name__ == '__main__':
    iterator = [1, 2, 3, 4, 5]
    k = 2

    result1 = apply_function_starmap(iterator, k)
    result2 = apply_function_partial(iterator, k)
    print(result1)
    print(result2)


