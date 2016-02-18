# CCC 2015 Junior 2: Happy or Sad
#
# Strings and counting
#

file = open("j2.8.in", "r")
line = file.readline()

# python makes this very easy with the count() function
##happy = line.count(":-)")
##sad = line.count (":-(")

# with other languages, find the happy and sad substrings
# with something like the following
happy = 0
sad = 0
for i in range(len(line)-2):
    if line[i:i+3] == ":-)":
        happy = happy + 1
    elif line[i:i+3] == ":-(":
        sad = sad + 1
      
if happy == 0 and sad == 0:
    print "none"
elif happy == sad:
    print "unsure"
elif happy > sad:
    print "happy"
else:
    print "sad"

