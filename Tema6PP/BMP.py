from Binary import Binary


class BMP(Binary):
    def __init__(self, path, freq,width,height,bpp):  # constructor
        super().__init__(path, freq)
        self.width = width
        self.height = height
        self.bpp = bpp
    def show_info(self):
        print("Path: " + self.path_absolut + "\n" 
              "Frecvente: " + self.frecvente + "\n" +
              "Width: " + self.width + "\n" +
              "Height: " + self.height + "\n" +
              "bpp: " + self.bpp + "\n" )
