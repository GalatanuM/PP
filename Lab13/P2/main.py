
class ExtendedStr(str):
    @property
    def to_pascal_case(self):
        words = self.split()
        pascal_case_words = [word.capitalize() for word in words]
        return ''.join(pascal_case_words)

if __name__ == '__main__':
    string = ExtendedStr("hello world")
    print(string.to_pascal_case)