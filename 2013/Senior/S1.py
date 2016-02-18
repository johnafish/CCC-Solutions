# CCC 2013 Senior 1: From 1987 to 2013

# find the next year after a given year with distinct digits.

# this is a big cheat: but python can count letters in a string :-)
def distinct(y):
    s = str(y)
    for digit in s:
        if s.count(digit) > 1:
            return False
    return True

file = open("s1.15.in", 'r')
year = int(file.readline()) + 1
while not distinct(year):
    year = year + 1
print year
