def is_prime(number):
    if number < 2:
        return False
    for i in range(2, int(number ** 0.5) + 1):
        if number % i == 0:
            return False
    return True
class StateMachine:
    def __init__(self, numbers):
        self.numbers = numbers
        self.state = 0

    def process(self):
        while self.state != "STOP":
            if self.state == 0:
                self.numbers = list(filter(lambda x: is_prime(x), self.numbers))
                if self.numbers:
                    self.state = 1
                else:
                    self.state = "STOP"
            elif self.state == 1:
                self.numbers = list(filter(lambda x: x % 2 != 0, self.numbers))
                if self.numbers:
                    self.state = 2
                else:
                    self.state = "STOP"
            elif self.state == 2:
                self.numbers = list(filter(lambda x: x < 50, self.numbers))
                if self.numbers:
                    self.state = "STOP"
                else:
                    self.state = "STOP"
        print(self.numbers)

numbers = [11 ,15, 2, 17,20, 19, 60, 70, 57]
automaton = StateMachine(numbers)
automaton.process()
