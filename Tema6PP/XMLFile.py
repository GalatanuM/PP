from TextASCII import TextASCII


class XMLFile(TextASCII):
    def __init__(self, path, freq,tag):  # constructor
        super().__init__(path, freq)
        self.first_tag = tag
    def get_first_tag(self):
        return self.first_tag
