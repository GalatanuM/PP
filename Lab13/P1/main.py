
def is_prime(num):
    if num < 2:
        return False
    for i in range(2, int(num ** 0.5) + 1):
        if num % i == 0:
            return False
    return True
class ExtendedInt(int):
    @property
    def is_prime(self):
        return is_prime(self)

if __name__ == '__main__':
    num = ExtendedInt(7)
    print(num.is_prime)

    num = ExtendedInt(10)
    print(num.is_prime)
