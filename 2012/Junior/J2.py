# J2 2012: Sounds fishy!
#
# a simple decision.
#

a = input()
b = input()
c = input()
d = input()

if a == b == c == d:
    print "Fish At Constant Depth"
elif a < b < c < d:
    print "Fish Rising"
elif a > b > c > d:
    print "Fish Diving"
else:
    print "No Fish"
    
