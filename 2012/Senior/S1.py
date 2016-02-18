# S1 2012: Don't Pass Me the Ball
#
# this is due to Daniel Cressman
#
# This is a combination problem, as any Grade 12
# who has taken Data Management will tell you.
#
# the formula is C(n,r) = n! / ((n-r)! * r!)
# for n object, taken r at a time.
#
# n = the given number - 1
# r = 3
#
# therefore the answer is (n)(n-1)(n-2) / 6

file = open("s1.5.in", 'r')
n = eval(file.readline()) - 1
print (n*(n-1)*(n-2))/6
