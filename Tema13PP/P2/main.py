from itertools import groupby

text = "cuvinte random scrise chiar de mine, mai random nu se poate"

def map_func(word):
    return (word[0], word)

def reduce_func(key_value):
    key, values = key_value
    return (key, list(values))

mapped = map(map_func, text.split())
sorted_mapped = sorted(mapped, key=lambda x: x[0])
grouped = groupby(sorted_mapped, key=lambda x: x[0])
result = map(reduce_func, grouped)

print(list(result))
