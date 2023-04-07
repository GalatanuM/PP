from GenericFile import GenericFile

class Binary(GenericFile):
    def __init__(self, path, freq): # constructor
        self.path_absolut = path
        self.frecvente = freq
    def get_path(self):
        return self.path_absolut
    def get_freq(self):
        return self.frecvente
