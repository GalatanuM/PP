from LeftSubclass import LeftSubclass
from RightSubclass import RightSubclass

class Subclass(LeftSubclass, RightSubclass):
    num_sub_calls = 0
    def call_me(self, caller):
        print("Apel metoda din Subclass, caller=", caller)
        LeftSubclass.call_me(self, "Subclass")
        RightSubclass.call_me(self, "Subclass")
        self.num_sub_calls += 1
