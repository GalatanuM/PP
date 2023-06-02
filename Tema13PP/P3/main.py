from functools import reduce

numbers = [1, 21, 75, 39, 7, 2, 35, 3, 31, 7, 8]

filtered_numbers = list(filter(lambda x: x >= 5, numbers))
print(filtered_numbers)

pairs = [(filtered_numbers[i], filtered_numbers[i + 1]) for i in range(0, len(filtered_numbers) - 1, 2)]
print(pairs)

multiplied_numbers = list(map(lambda pair: pair[0] * pair[1], pairs))
print(multiplied_numbers)

sum_of_results = reduce(lambda x, y: x + y, multiplied_numbers)
print(sum_of_results)