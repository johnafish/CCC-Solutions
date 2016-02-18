#
# This algorithm is by Sumudu Fernando
#
# He discovered that the other two algorithms have a flaw.
# The assumption that the minimum number of hydrants
# can be achieved by skipping the "largest gap"
# is false.

# A small testcase shows this:
# 
# 6
# 0
# 240000
# 292000
# 448000
# 500000
# 740000
# 2
# 
# The largest gap here is from the last house to the first,
# of length 260000.  The erroneous programs give an answer
# of 146000, while a hose of length 130000 is enough
# (one hydrant covers the first & last houses,
# while the second can cover the other four).
#
# this situation did NOT arise in the CCC test data,
# so the other solutions are technically "correct".
#
# BUT this is "more correct" :-)


STREET_LEN = 1000000

# Returns the minimum number of hydrants needed to service
# the given houses, assuming hoses of the given length.
#
# i is the starting house, and it can be any of the houses
# 0,1,2,3... which fall within the given hose diameter
#
# the internal j loop is tricky to understand, but it
# takes care of "wrap-around" and calculates the number of
# hoses needed starting at house i.

def hydrants(hose, houses):
    best = len(houses)
    diam = hose * 2
    
    i = 0
    while i < len(houses) and houses[i] <= houses[0] + diam:
        count = 1
        curEnd = houses[i]
        j = i + 1
        while j < len(houses) and (houses[i] > (houses[j] + diam - STREET_LEN)):
            if houses[j] > curEnd:
                count += 1
                curEnd = houses[j] + diam
            j = j + 1
        best = min(best, count)
        i = i + 1
    return best


# A standard binary search of the hose lengths
# does the trick.

file = open("s3.1.in",'r')
h = eval(file.readline())
houses = []
for i in range(h):
    houses.append(eval(file.readline()))
houses.sort()
k = eval(file.readline())  
lo = -1
hi = STREET_LEN
while (hi > (lo+1)):
    mid = (lo + hi) // 2
    if (hydrants(mid, houses) > k):
        lo = mid
    else:
        hi = mid
print hi
