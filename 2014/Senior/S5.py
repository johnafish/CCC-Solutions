# CCC 2014 Senior 5: Lazy Fox
#
# This solution is by Jacob Jackson of University of Toronto Schools
#
# This is Jackson's explaination of the code:
#
# Make a list of all points and add the origin to that list.
# Take all pairs of points and sort them in increasing order of distance.
# Process these pairs in order, keeping an array best[N],
# which represents the maximum number of treats attainable
# from a given location if you can't travel farther than the current distance
# (the current distance = the distance between the two points
# you're currently processing).
# For each pair (A, B), consider traveling from A to B or from B to A. That is,
#     best[A] = max(best[A], best[B] + 1);
#     best[B] = max(best[B], best[A] + 1);
#
# Here is a trace of the variables as it processes the example data set
# given with the problem statement:
#
#   pairs [[1, 3, 4], [1, 4, 5], [4, 3, 5], [5, 1, 2], [10, 0, 3],
#          [13, 0, 4], [18, 0, 5], [29, 1, 5], [40, 1, 4], [50, 2, 5],
#          [53, 1, 3], [65, 2, 4], [82, 2, 3], [89, 0, 1], [116, 0, 2]]
#
#   pair= [1, 3, 4]
#   pdist= [0, 0, 0, 1, 1, 0]   pbest= [0, 0, 0, 0, 0, 0]   best= [0, 0, 0, 1, 1, 0]
#   
#   pair= [1, 4, 5]
#   pdist= [0, 0, 0, 1, 1, 1]   pbest= [0, 0, 0, 0, 0, 0]   best= [0, 0, 0, 1, 1, 1]
#   
#   pair= [4, 3, 5]
#   pdist= [0, 0, 0, 4, 1, 4]   pbest= [0, 0, 0, 1, 0, 1]   best= [0, 0, 0, 2, 1, 2]
#   
#   pair= [5, 1, 2]
#   pdist= [0, 5, 5, 4, 1, 4]   pbest= [0, 0, 0, 1, 0, 1]   best= [0, 1, 1, 2, 1, 2]
#   
#   pair= [10, 0, 3]
#   pdist= [10, 5, 5, 10, 1, 4]   pbest= [0, 0, 0, 2, 0, 1]   best= [2, 1, 1, 2, 1, 2]
#   
#   pair= [13, 0, 4]
#   pdist= [13, 5, 5, 10, 13, 4]   pbest= [2, 0, 0, 2, 1, 1]   best= [2, 1, 1, 2, 1, 2]
#   
#   pair= [18, 0, 5]
#   pdist= [18, 5, 5, 10, 13, 18]   pbest= [2, 0, 0, 2, 1, 2]   best= [2, 1, 1, 2, 1, 2]
#   
#   pair= [29, 1, 5]
#   pdist= [18, 29, 5, 10, 13, 29]   pbest= [2, 1, 0, 2, 1, 2]   best= [2, 3, 1, 2, 1, 2]
#   
#   pair= [40, 1, 4]
#   pdist= [18, 40, 5, 10, 40, 29]   pbest= [2, 3, 0, 2, 1, 2]   best= [2, 3, 1, 2, 4, 2]
#   
#   pair= [50, 2, 5]
#   pdist= [18, 40, 50, 10, 40, 50]   pbest= [2, 3, 1, 2, 1, 2]   best= [2, 3, 3, 2, 4, 2]
#   
#   pair= [53, 1, 3]
#   pdist= [18, 53, 50, 53, 40, 50]   pbest= [2, 3, 1, 2, 1, 2]   best= [2, 3, 3, 4, 4, 2]
#   
#   pair= [65, 2, 4]
#   pdist= [18, 53, 65, 53, 65, 50]   pbest= [2, 3, 3, 2, 4, 2]   best= [2, 3, 5, 4, 4, 2]
#   
#   pair= [82, 2, 3]
#   pdist= [18, 53, 82, 82, 65, 50]   pbest= [2, 3, 5, 4, 4, 2]   best= [2, 3, 5, 6, 4, 2]
#   
#   pair= [89, 0, 1]
#   pdist= [89, 89, 82, 82, 65, 50]   pbest= [2, 3, 5, 4, 4, 2]   best= [3, 3, 5, 6, 4, 2]
#   
#   pair= [116, 0, 2]
#   pdist= [116, 89, 116, 82, 65, 50]   pbest= [3, 3, 5, 4, 4, 2]   best= [5, 3, 5, 6, 4, 2]
#
#
# Times of the test data sets on my Pentium 4, 3.2Ghz processor.
#     1 to 9 all well less than 1 sec
#     10 = 5.5 sec
#     11 = 25 sec
#     12 = 16 sec
#     13 = 20 sec
#     14 = 20 sec
#     15 = 26 sec
# Several other people sent me solutions, but
# NONE were as fast as this. Not even close. So if you think you can
# do better, run this on your machine as a benchmark and 
# write a faster PYTHON program. And no fair using anything
# built-in other than sort() and normal array processing. :-)


file = open ("s5.15.in", "r")
N = int(file.readline())

pts = [[0, 0]]
for i in range(N):
    pt = file.readline().split()
    pts.append([int(pt[0]), int(pt[1])])

pairs = []
for a in range(N+1):
    for b in range(a+1, N+1):
        dx = pts[a][0] - pts[b][0]
        dy = pts[a][1] - pts[b][1]
        pairs.append([dx * dx + dy * dy, a, b])

pairs.sort()

best  = [0] * (N+1)
pbest = [0] * (N+1)
pdist = [0] * (N+1)

for pair in pairs:
    d = pair[0]
    a = pair[1]
    b = pair[2]

    if d != pdist[a]:
        pdist[a] = d
        pbest[a] = best[a]
    if d != pdist[b]:
        pdist[b] = d
        pbest[b] = best[b]

    if a == 0: # the origin is a special case because we cannot revisit it
        best[a] = max(best[a], pbest[b])
    else:
        best[a] = max(best[a], pbest[b] + 1)
        best[b] = max(best[b], pbest[a] + 1)    

print best[0] + 1
