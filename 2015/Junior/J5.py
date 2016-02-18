# CCC 2015 Junior 5: Pie Day
#
# This is a recursion problem (or possibliy a Dynamic Programming problem)
#
# This is the recursive solution. 
# 
# Given n (number of pieces of pie) and k (number of people) and the minimum
# number of pieces that can be taken, the number of ways the pie can be destributed
# is:
#     1 if n = k or k = 1
#     otherwise it is the sum of the ways of (n-i, k-1, i) where i goes from
#                min to n/k (inclusive)
#
# an example might help: pi(9,4,1)
#   n = 9, k = 4 and min = 1
#   for this the answer is calculated as follows:
#       the first person could take 1 piece leaving us the task of finding the ways
#                with now 8 pieces (9-1), 3 people and a min of 1 (= pi(8,3,1))
#       the first person could take 2 pieces leaving us the task of finding the ways
#                with now 7 pieces (9-2), 3 people and a min of 2 (= pi(7,3,2))
#       the first person could NOT take 3 pieces (or more)_because that would leave 6
#                pieces for 3 people and since the first person took 3, they
#                would have to take at least 3 themselves and that's 9 not 6 pieces!
#                in general, the most the first person person can take is
#                                               n/k (eg 9/4 = 2)
#
# The recursion as described above works fine, BUT is too slow for the
# later data sets, SO to avoid calculating the same thing twice
# there is a "visited" array. This is a large 3D array keeping track of all
# previous results. Half the running time (and code) is devoted to creating it 
# and initializing it to 0. In the recursion, if the visited array knows the answer,
# use it and don't recurse, otherwise calculate and store the answer in visited. 
#
# the worst case, data set 10, takes about 3 sec on my old
#          XP Pentium 4 CPU, 3.2Ghz, 1G RAM machine, using Python. 

visited = []

def pi(n,k,min):
    if visited [n][k][min] == 0:       
        if n == k:
            visited[n][k][min] = 1
        elif k == 1:
            visited[n][k][min] = 1
        else:
            t = 0
            for i in range (min, (n / k)+1):
                t = t + pi(n-i, k-1, i)
            visited[n][k][min] = t
    return visited[n][k][min]


file = open("j5.10.in", "r")
n = int(file.readline())
k = int(file.readline())

for i in range(n+1):
    x = []
    for j in range(k+1):
        t = []
        for kk in range(n+1):
            t.append (0)
        x.append(t)
    visited.append(x)

print pi(n,k,1)


