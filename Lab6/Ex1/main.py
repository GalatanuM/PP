from Contact import Contact
from Friend import Friend
from Agenda import Agenda


if __name__ == '__main__':
    agenda = Agenda()
    agenda.add_contact(Contact('Ion Popescu','ion_popescu@gmail.com'))
    agenda.add_contact(Contact('Ion Ionescu','ion_ionescu@gmail.com'))
    agenda.add_friend((Friend('Marin Abc','marin.abc@gmail.com','0746372893')))
    agenda.add_friend((Friend('Sorin Def','sorin.def@gmail.com','0745273849')))
    agenda.print_agenda()