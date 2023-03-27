from BaseClass import BaseClass

class LeftSubclass(BaseClass):
    num_left_calls = 0
    def call_me(self, caller):
        print("Apel metoda din LeftSubclass, caller=", caller)
        BaseClass.call_me(self, "LeftSubclass")
        self.num_left_calls += 1
