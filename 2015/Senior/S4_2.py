# CCC 2015 Senoir 4: Convex Hull
#
# written by Daniel Whitney, Newmarket High School
#
# NOTE: This is in python 3, tho I believe it would take very little
#       to convert it to python 2 (switch range to xrange, print() to
#       print).
#
# This problem being a shortest path problem, I used Dijkstra's algorithm
# with some modifications to find it.
# Read:
#    en.wikipedia.org/wiki/Dijkstra's_algorithm#Algorithm
# for a quick description of how the basic algorithm works.
#
# From now on I am going to assume you know how Dijkstra's algorithm works
# so I can explain the modifications.
#
# First we have to recognize instead of simply storing the minimum
# distance to each island, we are going to have to store the minimum
# distance with X (X is 1 to 200) hull remaining to each point.  And when
# we would add a point to the que that has a negative or zero hull value,
# we simply skip it.  Thes additional states simply add more nodes to the
# graph, but when we're checking for the endpoint we check only to see if
# the current island is the correct ending island.  In this case the first
# time the ending island is removed from the que, we know we've found the
# smallest distance.
#
# For implementing the binary trees, refer to:
#    en.wikipedia.org/wiki/Binary_heap#Heap_operations
#
# I found this page explained them fairly thoroughly.



def pop(heap):
    popped = heap[0]
    heap[0] = heap[-1]
    heap.pop()
    lq = len(heap)
    node = 0
    while node < lq:
        minimum = heap[node]
        nxt_node = node
        l_node = node*2+1
        r_node = node*2+2
        if l_node < lq and heap[l_node] < minimum:
            minimum = heap[l_node]
            nxt_node = l_node
        if r_node < lq and heap[r_node] < minimum:
            minimum = heap[r_node]
            nxt_node = r_node
        if node != nxt_node:
            heap[node], heap[nxt_node] = heap[nxt_node], heap[node]
            node = nxt_node
        else:
            node = lq
    return popped


def push(heap, item):
    heap.append(item)
    lq = len(heap)
    node = lq-1
    while node > 0:
        nxt_node = (node-1)//2
        if heap[nxt_node] > heap[node]:
            heap[node], heap[nxt_node] = heap[nxt_node], heap[node]
            node = nxt_node
        else:
            node = 0


file = open('s4.15.in')
K, N, M = map(int, file.readline().split())
routes = [{} for n in range(N)]
for m in range(M):
    a, b, t, h = map(int, file.readline().split())
    routes[a-1].setdefault(b-1, []).append((t, h))
    routes[b-1].setdefault(a-1, []).append((t, h))


INF = 10**10


p, q = map(int, file.readline().split())
p, q = p-1, q-1

min_time = INF
distance = [[INF for i in range(200+1)] for n in range(N)]
distance[p][K] = 0
visited = set()
que = [(0, p, K)]
while que:
    island = pop(que)
    if island[1] == q:
        min_time = island[0]
        break
    if island[1:] in visited:
        continue
    visited.add(island[1:])
    for destination in routes[island[1]]:
        for route in routes[island[1]][destination]:
            to_add = (distance[island[1]][island[2]] + route[0], destination, island[2] - route[1])
            if to_add[2] > 0:
                if to_add[0] < distance[to_add[1]][to_add[2]]:
                    distance[to_add[1]][to_add[2]] = to_add[0]
                push(que, to_add)
if min_time == INF:
    min_time = -1

print(min_time)
