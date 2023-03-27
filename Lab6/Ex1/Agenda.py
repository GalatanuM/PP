from ContactList import ContactList
from FriendList import FriendList

class Agenda:
    all_contacts = ContactList()
    all_friends = FriendList()

    def add_contact(self, contact):
        self.all_contacts.append(contact)
    def add_friend(self,friend):
        self.all_friends.append(friend)
    def print_agenda(self):
        for contact in self.all_contacts:
            print(contact)
        for contact in self.all_friends:
            print(contact)