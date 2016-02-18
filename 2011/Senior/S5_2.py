#  CCC 2011 S5: Switch
#  solution by Sumudu Fernando
#
#  We use a dynamic programming approach; this solution is O(N).
#
#  First, we create a set of groups of on lights.
#
#  Considering two such groups, if their span (distance from the left of the
#  leftmost group to the right of the rightmost one) is 4 or 5, we can always
#  turn off all groups in that span by filling in the blanks.
#
#  Similarly, for spans of 6 and 7, it is possible to clear the span as a block
#  as long the middle lights (3rd & 4th for span 6 (eg: 101101),
#  or 4th for span 7 eg. 1101001) are off.
#
#  So, if we have such a "clearable" span of length "len"  with "numOnes" lights
#  on, it will take "len - numOnes" switches (t) to clear it as a block. 
#  If the span is less than 4, we may simply take "len" as 4 (which means
#  we'll turn on lights outside the span).  
#
#  in simple terms, determine the number of lights to switch on, t, for a
#  group (or groups working right from a starting group) and then the answer is
#  the minimum of what we have already for this group (which starts very large)
#  and t + the answer we had for the next group to the right.
#
#  suppose we are looking at group 3 (i=3)
#  we will look at the groups 3, then 3 and 4, then 3, 4 and 5 (stopping when that
#                                  collection gets be be greater than 7 long)
#  for group 3, we consider (t + answer for group 4)
#  for group 3 and 4, we consider (t + the answer for group 5)
#  etc...  taking  the minimum answer. 
#
#
#  Here is a trace of an example with lights:  111011101000111
#
#  group = [[0, 3], [4, 7], [8, 9], [12, 15]]
#
#  processing the first (rightmost) group (111)
#     i= 3  j= 3  numOnes= 3   t = 1  minimumSwitches: [0, 0, 0, 1, 0]
#
#  processing the next group  (1000111)
#     i= 2  j= 2  numOnes= 1   t = 3  minimumSwitches: [0, 0, 4, 1, 0]
#     i= 2  j= 3  numOnes= 4   t = 3  minimumSwitches: [0, 0, 3, 1, 0]
#
#  next   (11101000111)
#     i= 1  j= 1  numOnes= 3   t = 1  minimumSwitches: [0, 4, 3, 1, 0]
#       we got the 4 because t = 1 (there are 3 lights on in the first group)
#                                  + 3 from the later blocks, ms[2]=3
#                   and took the min of INF and 4
#     i= 1  j= 2  numOnes= 4   t = 1  minimumSwitches: [0, 2, 3, 1, 0]
#       we got the 2 because t = 1 (we are now looking at 11101)
#                                  + 1 from the later blocks, ms[3]=1
#                   and took the min of 4 and 2
#
#  last  (111011101000111)
#     i= 0  j= 0  numOnes= 3   t = 1  minimumSwitches: [3, 2, 3, 1, 0]
#     i= 0  j= 1  numOnes= 6   t = 1  minimumSwitches: [3, 2, 3, 1, 0]


INF = 1000000

file = open("s5.6.in", 'r')
K = eval(file.readline())
lights = []
for i in range(0,K):
    lights.append(eval(file.readline()))
group = []
N = 0
group.append ([0, 0])

for i  in range (0, K):
    if lights[i] == 1:
        if group[N][1] == 0:
            group[N][0] = i
            group[N][1] = i
        group[N][1] = group[N][1] + 1
    elif group[N][1] != 0:
        N = N + 1
        group.append ([0, 0])
        
if group[N][1] == 0:
    N = N - 1
    group.pop()

N = N + 1
minimumSwitches = []
for i in range(0,N+1):
    minimumSwitches.append (0)

numL = 0

for i in range(N-1, -1, -1):
    minimumSwitches[i] = INF;
    numOnes = 0;
    # we try every group as the rightmost in the block until we get too far away
    j = i
    while j < N and ((group[j][1] - group[i][0]) <= 7):
        numOnes = numOnes + group[j][1] - group[j][0]
        len = max(4, group[j][1] - group[i][0]);
        t = len - numOnes

        # for spans of 6 and 7 there are certain configurations that cannot be cleared as a block
        if len == 6 and lights[group[i][0] + 2] == 1 and lights[group[i][0] + 3] == 1:
            t = INF
        elif len == 7 and lights[group[i][0] + 3] == 1:
            t = INF

        minimumSwitches[i] = min(minimumSwitches[i], t + minimumSwitches[j+1])

        j = j + 1

print minimumSwitches[0]
