# CCC 2011 Junior 2: Who has seen the wind
# written by C. Robart
# March 2011

# simple loop and if

h = input()
M = input()

t = 1
A = -6*t*t*t*t + h*t*t*t + 2*t*t + t
while t < M and A > 0:
    t = t + 1
    A = -6*t*t*t*t + h*t*t*t + 2*t*t + t
if A > 0:
    print "The balloon does not touch ground in the given time."
else:
    print "The balloon touches ground at hour:\n" , t
