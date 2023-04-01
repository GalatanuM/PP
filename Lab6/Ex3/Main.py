import os

class AudioFile:
    def __init__(self, filename):
        if not filename.endswith(self.ext):
            raise Exception("Format nesuportat")
        self.filename = filename

class MP3File(AudioFile):
    ext = "mp3"
    def play(self):
        print("se canta {} un mp3".format(self.filename))
class WavFile(AudioFile):
    ext = "wav"
    def play(self):
        print("se canta {} un wav".format(self.filename))
class OggFile(AudioFile):
    ext = "ogg"
    def play(self):
        print("se canta {} un ogg".format(self.filename))
class FlacFile:
    def __init__(self, filename):
        if not filename.endswith(".flac"):
            raise Exception("Format necunoscut")
        self.filename = filename
    def play(self):
        print("se canta {} un flac".format(self.filename))

if __name__ == '__main__':

    mp3 = MP3File
    wav = WavFile
    ogg = OggFile
    flac = FlacFile
    path = input("path fisier: ")

    if os.path.isfile(path):

        file = os.path.basename(path)

        if file.endswith("mp3"):
            mp3=MP3File(file)
            mp3.play()

        elif file.endswith("wav"):
            wav=WavFile(file)
            wav.play()

        elif file.endswith("ogg"):
            ogg=OggFile(file)
            ogg.play()

        elif file.endswith("flac"):
            flac=FlacFile(file)
            flac.play()

        else:
            print("Format necunoscut!")
            
    else:
        print("Format necunoscut!")