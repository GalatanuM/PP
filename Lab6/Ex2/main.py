from Subclass import Subclass


if __name__ == '__main__':
    subclass_instance = Subclass()
    print(Subclass.__mro__) # method resolution order
    subclass_instance.call_me("__main__")
    print(subclass_instance.num_sub_calls,
        subclass_instance.num_left_calls,
        subclass_instance.num_right_calls,
        subclass_instance.num_base_calls)