class FriendList(list):
    def search(self, name):
        matching_friends = []
        for friend in self:
            if friend in friend.name:
                matching_friends.append(friend)
            return matching_friends