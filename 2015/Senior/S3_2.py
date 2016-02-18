# CCC 2015 Senior 3: Gates
#
# This solution is by Weiwei Zhong
#
# This uses a disjoint-set Data Structure
#
# see  http://community.topcoder.com/tc?module=Static&d1=tutorials&d2=disjointDataStructure
# for a good exlaination of the data structure
#
# to help explain this, look at the following input data set:
#   6 7 6 1 4 4 6 6 3   (6 gates and 7 planes.)
#
# root is initialized to [0, 0, 0, 0, 0, 0, 0]  (there are more 0's, but forget them)
#
# After reading the number of gates, root is [0, 1, 2, 3, 4, 5, 6]
# think of each number being its own set. 
#
# The first plane goes in gate 6 and its found in its own set, so "take it",
# by merging it with the set below it. 
# so set 6 and 5 are merged and root becomes [0, 1, 2, 3, 4, 5, 5]
# The way to "read" this is that set 6 doesn't exist any more and if part of the "5" set.
# a set is identified with the lowest number in the set.
#
# The same thing happens with the 1 and the 4, they go in their respective gates
# and those gates are merged with the sets below them.
# this makes root                            [0, 0, 2, 3, 3, 5, 5]
#
# The following 4 finds that 4 is part of the 3 set. We haven't had that before.
# In this case merge changes root to become  [0, 0, 2, 2, 3, 5, 5]
# (now stop a minute and look at that: merge sets root[find(x)] = find(y)
#  or in our case                                 root[find(3)] = find[2]
#  which means                                    root[3] = 2
# A way to "read" this is that in root, if x = root[x] then gate x is available.
# and now after 4 planes, only root 2 and 5 remain. Or if you prefer the world of sets
# we have 3 sets (0,1), (2,3,4) and (5,6)
#
# With the next plane wanting 6 again we can see that gate 5 will be taken and we'll be
# down to only two sets (0,1) and (2,3,4,5,6), the only "open" gate will be 2.
# After the merge, root becomes              [0, 0, 2, 2, 2, 2, 5]
# How did that happen? Well it's complex due to the recursion happening in find, BUT
# we can see that there is only one open gate now, 2, and that we have two sets.
#
# With the third palne wanting 6, the last open gate, 2 is taken and root becomes
#                                            [0, 0, 0, 2, 2, 2, 2]
# which is a single set represented by 0, no gates left.
#
# The next plane request for gate 3 stops the program, and i = 6 is printed.
#
# I realize this is as clear as mud: Go to the website and read! :-)


root = [0 for i in range(100001)]

#find the set this index belongs to
def find(x): 
    if x!=root[x]:
        root[x] = find(root[x])
    return root[x]

#merge index x into y
def merge(x,y): 
    root[find(x)] = find(y)

file = open("s3.10.in")    
numGates = int(file.readline())
numPlanes = int(file.readline())
for i in range(1,numGates+1):
    root[i] = i
i = 0
keepgoing = True
while i < numPlanes and keepgoing:
    gateWanted = int(file.readline())
    gateWantedRoot = find(gateWanted)
    if gateWantedRoot == 0:
        print i
        keepgoing = False 
    merge(gateWantedRoot,gateWantedRoot-1)
    i = i + 1
if keepgoing:    
    print numPlanes