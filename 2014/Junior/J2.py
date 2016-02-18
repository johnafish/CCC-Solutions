# CCC 2014 Junior 2: Vote Count
#
# straight forward string processing
#

file = open ("j2.4b.in", "r")
v = int(file.readline())
votes = file.readline()

# in python the following loop could be replaced with
# acount = votes.count("A")
acount = 0
for letter in votes:
    if letter == "A":
        acount = acount + 1

bcount = v - acount
if acount > bcount:
    print "A"
elif bcount > acount:
    print "B"
else:
    print "Tie"
