from BaseClass import BaseClass

class RightSubclass(BaseClass):
    num_right_calls = 0
    def call_me(self, caller):
        print("Apel metoda din RightSubclass, caller=", caller)
        BaseClass.call_me(self, "RightSubclass")
        self.num_right_calls += 1