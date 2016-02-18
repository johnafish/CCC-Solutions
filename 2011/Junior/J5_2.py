# CCC 2011 Junior 5: Unfriend
# algorithm by Sumudu Fernando
# March 2011

# Let
# ways(x) = the number of ways to unfriend the people under x.
#
# To calculate ways(x), note that x could either remove y (that's 1 way)
# or a set of people under y (that's ways(y)), for each y that x invited.
# So that means ways(x) = the product of (1+ways(y)) for each y that x
# invited.

# lets me work through an example J4.3.in
# 1 was invited by 4
# 2 was invited by 4
# 3 was invited by 5
# 4 was invited by 6
# 5 was invited by 6

# building the ways array from the ground up:
# ways(1) = 1 (he can unfriend none)
# ways(2) = 1 (he can unfriend none)
# ways(3) = 1 (he can unfriend none)
# ways(4) = (1+ways(1)*(1+ways(2)) = 4
#         a closer look: 4 can unfriend (1 or none) * (2 or none)
#         of if you like: 12, 1, 2, none
# ways(5) = (1+ways(3) = 2
#         a closer look: 5 can unfriend 3 or none
# ways(6) = (1+ways(4)*(1+ways(5)) = 15
#         a closer look: 6 can unfriend 4, ie: 124 or
#                           those under 4: 12, 1, 2, none
#                            = (124, 12, 1, 2, none)
#                        * unfriend 5, ie: 35 or 
#                           those under 5: 3, none
#                            = (35, 3, none)
#         and if you * those two sets you get:
#               (12345,1235,135,235,35,1234,123,13,23,3,124,12,1,2,none)
#
#
# after that, coding is a joke.
#

# input the info
N = input()
ways = [1,1,1,1,1,1,1]
for i in range(1,N):
    y = input()
    ways[y] = ways[y] * (1 + ways[i])

print ways[N]



