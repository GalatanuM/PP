
class BaseClass:
    num_base_calls = 0
    def call_me(self, caller):
        print("Apel metoda din BaseClass, caller=", caller)
        self.num_base_calls += 1