# CCC 2014 Junior 1: Triangle Times
#
# Straight forward else if structure
#

file = open("j1.6.in", "r")
a = int(file.readline())
b = int(file.readline())
c = int(file.readline())
if a + b + c != 180:
    print "Error"
elif a == 60 and b == 60 and c == 60:
    print "Equilateral"
elif a == b or a== c or b == c:
    print "Isosceles"
else:
    print "Scalene"
