# CCC 2013 Senior 4: Who is Taller?
#
# relatively straight forward Breadth First Search thru a "tree".
#
# Generally speaking The BFS approach is likely better than
# a Depth First Search that a recursive approach would take. 
# Going deep, could lead to a stack overflow, while the BFS
# approach avoids that. Also in this case the BFS is very easy 
# to implement. 


# returns true if p is taller than q
# uses a Breadth first Search thru the "a" tree of size N
def taller(a, N, p, q):
    # v is a "visited" list for track (don't go to same guy twice)
    v = []
    for r in range(N):
        v.append(False)    
    queue = []
    for x in a[p]:
        queue.append (x)
    while len(queue) > 0:
        h = queue.pop(0)
        if h == q:
            return True
        if not v[h]:
            v[h] = True
            for x in a[h]:
                queue.append(x)
    return False
        

file = open("s4.6-5.in", 'r')
x = file.readline().strip().split()
N = int(x[0])
M = int(x[1])

# a is the main array holding all the links
a = []
for r in range(N):
    row = []
    a.append(row)


for i in range(M):
    x = file.readline().strip().split()
    p = int(x[0]) - 1
    q = int(x[1]) - 1
    a[p].append(q)

x = file.readline().strip().split()
p = int(x[0]) - 1
q = int(x[1]) - 1

if taller(a, N, p, q):
    print "yes"
elif taller(a, N, q, p):
    print "no"
else:
    print "unknown"
