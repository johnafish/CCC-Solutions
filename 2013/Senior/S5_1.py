# CCC 2013 Senior 5: Factor Solitaire
#
# this is the straight forward, figure out the min cost for each number
# from 1 to the given number.
#
# s5.12.in in about 48sec.
# s5.13.in will be far too slow....
# therfore this "solution" gets 12/15.
#
# It could be improved by finding the prime factorization of each number
# and from those prime factors find all the combos to create all the
# factors, but that's a real pain to do, and it could still be too
# slow. 


import math

# returns a list of factors of x
def factors(x):
    f = [1, x]
    r = int(math.sqrt(x)) + 1
    for i in range (2,r):
        if x % i == 0:
            f.append(i)
            f.append(x / i)
    return f

file = open("s5.12.in", 'r')
N = int(file.readline().strip())
    
# m is a list of max values for each number
m = []
for i in range(N+1):
    m.append(9999999999)

m[1] = 0
for i in range(1, N+1):
    f = factors(i)
    for x in f:
        h = i + x
        cost = m[i] + (i / x)
        if h <= N and cost < m[h]:
            m[h] = cost
    
print m[N]
