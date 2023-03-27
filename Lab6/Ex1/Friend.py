from Contact import Contact

class Friend(Contact):
    def __init__(self, name, email, phone):
        super().__init__(name, email)
        self.phone = phone
    def __repr__(self):
        #super().__repr__()(name,email)
        return "Friend({}, {}, {})".format(self.name, self.email, self.phone)