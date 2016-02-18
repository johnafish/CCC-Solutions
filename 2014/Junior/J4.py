# CCC 2014 Junior 4: Party Invitation
#
# relatively straight forward 1D array processing
# (element removal and modulo arithmetic)
#

file = open ("j4.5.in", "r")
k = int(file.readline())

#create friends array [1...k]
friends = []
for i in range (k):
    friends.append(i+1)

m = int(file.readline())

for round in range(m):
    r = int(file.readline())

    # eliminate every rth friend
    newfriends = []
    for i in range(len(friends)):
        if (i+1) % r != 0:
            newfriends.append (friends[i])

    # copy the new friends back into the old one
    friends = []
    for f in newfriends:
        friends.append(f)

for f in friends:
    print f
