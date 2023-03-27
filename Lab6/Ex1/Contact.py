class Contact:
    def __init__(self, name, email): # constructor
        self.name = name
        self.email = email
    # echivalentul supraincarcarii operator<< din c++
    def __repr__(self):
        return "Contact({}, {})".format(self.name, self.email)