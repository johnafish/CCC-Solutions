# CCC 2011 Junior 5: Unfriend
# written by C. Robart
# March 2011

# this is a process of determining which sets are possible
# to remove. Each set can be considered an integer. Since max N = 6,
# the max number is 12345. (Or for n = 4 the max = 123)
# Each number from 0 to the max will be considered:
#  - all numbers with digits >= N are discarded.  (eg 57)
#  - all numbers with digits which do not INCREASE are discarded (eg 223)
#  - all numbers which do not contain the number of a person invited
#    by a person who is in the number is discarded.
#        (eg if 3 invited 1, then 23 is discarded)
#
#  A count of all the numbers remaining is the answer. 

# this chacks a given number i to ensure that it
# has no digits equal or greater than N and the digits
# all increase in value left to right
def goodNumber(N, x):
    last = N
    ok = True
    while x > 0 and ok:
        digit = x % 10
        ok = digit < last
        last = digit
        x = x // 10
    return ok

# this checks that all the digits of a number i
# are related thru list l. For example if 4 invites 2, then any number
# with a 4 digit must also have a 2 digit.
def goodCombo(N,x,l):

    # build the list of the digitis of x
    digits = []
    while x > 0:
        digits.append(x % 10)
        x = x // 10

    # for each digit of x, see if it "invited" anyone,
    # if so, that invitee must be in the list of digits.
    for d in digits:
        for x in range(0,N-1):
            if d == l[x]:
                if (x+1) not in digits:
                    return False
    return True

# input the info
N = input()
l = []
for i in range(1,N):
    l.append(input())

# count the valid numbers (or sets)
max = eval("12345"[0:N-1])
count = 1
for i in range(1, max+1):
    if goodNumber(N, i) and goodCombo(N, i, l):
        count = count + 1
print count
        

        


