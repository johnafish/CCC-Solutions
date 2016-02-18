# CCC 2009
#
# S1: Cool Numbers
#
# This is a FASTER method still.
# by Goutam Venkatramanan
#
# If x = a^3 and x = b^2 then a^3 = b^2 or a^3/b^2 = 1
#
# That means that if a could be factored into n*m you'd have
# n*m*n*m*n*m/b*b = 1. But wait a minute.... that doesn't work.
# There is no way to factor b to cancel the 3 m's and 3 n's....
# Nope, the only way is if a factors into n*n and b into n*n*n
#
# so that means x = n^6.  QED
#
# SO this problem means: find all the numbers^6 that are in the range
# up to 100,000,000
#
# Goutram's insight was that there are very few of these, in fact
# n = 22 means n^6 = 113,379,905,
# so 21 is the max number you need to test.

file = open("s1.5.in", "r")
a = int(file.readline())
b = int(file.readline())
count = 0
for n in range(1,22):
    i = n**6
    if (i >= a) and (i <= b):
        count+=1
print count

