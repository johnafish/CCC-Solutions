# CCC 2015 Junior 1: Special Day
#
# Straight forward else if structure
#

file = open("j1.1.in", "r")
month = int(file.readline())
day = int(file.readline())

if month == 2 and day == 18:
    print "Special"
elif month < 2 or (month == 2 and day < 18):
    print "Before"
else:
    print "After"
