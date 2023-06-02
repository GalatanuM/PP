from functools import reduce

numbers = [2,3,5,7,11,15,16,18,19,20,60,70,53]

is_prime = lambda x: all(x % i != 0 for i in range(2, int(x ** 0.5) + 1))

def process_list(numbers):
    state = 0
    while numbers:
        if state == 0:
            numbers = list(filter(lambda x: is_prime(x), numbers))
            state = 1
        elif state == 1:
            numbers = list(filter(lambda x: x % 2 != 0, numbers))
            state = 2
        elif state == 2:
            numbers = list(filter(lambda x: x < 50, numbers))
            state = 3
        elif state == 3:
            break
    return numbers

processed_numbers = process_list(numbers)
print(processed_numbers)
