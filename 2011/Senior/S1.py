# CCC 2011 Senior 1: English or French
# written by C. Robart
# March 2011

# straight forward file handling with
# simple character counting in strings

file = open("s1.5.in", 'r')
lines = file.readlines(100000)
countS = 0
countT =0
for line in lines:
    for i in range(0, len(line)):
        if line[i] == "s" or line[i] == "S":
            countS = countS + 1
        elif line[i] == "t" or line[i] == "T":
            countT = countT + 1
if countT > countS:
    print "English"
else:
    print "French"
file.close()
