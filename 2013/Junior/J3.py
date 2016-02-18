# CCC 2013 Junior 3: From 1987 to 2013

# find the next year after a given year with distinct digits.

# this is a big cheat: but python can count letters in a string :-)
def distinct(y):
    s = str(y)
    for digit in s:
        if s.count(digit) > 1:
            return False
    return True

year = input() + 1
while not distinct(year):
    year = year + 1
print year
