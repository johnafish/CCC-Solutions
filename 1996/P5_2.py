# CCC 1996 Problem E: Maximum Distance
#
# This algorithm is by Ahmed Sabie
#
# Quite straight forward problem and solution.
#
# The key problem is:
# Given an x[i], find the last number in an array y, such that y[j] >= x[i],
# (j is largest number possible)
#
# Ahmed's approach is to use a binary search versus a linear search.
#
# The binary search will put leave mid at the largest index such that
# y[mid] >= x[i]

file = open ("max.in", "r")
testCases = int(file.readline())
for k in range(testCases):
    currentMax = 0
    n = int(file.readline())
    x = file.readline().split()
    x = map(int, x)
    y = file.readline().split()
    y = map(int, y)

    for i in range(n):
        low = 0
        high = n - 1
        while low <= high:
            mid = (low + high) / 2
            if y[mid] >= x[i]:
                low = mid + 1
            else:
                high = mid - 1

        currentMax = max (currentMax, mid - i)

    print currentMax
