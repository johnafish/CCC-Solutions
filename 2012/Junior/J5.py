# J5 2012: A Coin Game
#
# this is a brute force, Breadth First Search
# of the game tree.
#
# Each "move" is an array strings
#
# Each move is stored in a tree (a big array)
# along with the level of the move in the tree.
#
# This WILL work VERY quickly for up to 4 coins.
# (and completely DIES at 5 coins :-) so see S4.)

n = 0

class Node:
    def __init__(self, m, l):
        self.m = m
        self.level = l

def done (move):
    global n
    i = 0
    while i < n and move[i] == str(i+1):
        i = i + 1
    return i == n 

# given a move, see if it is already in the tree        
def inTree(t, m):
    for x in t:
        if x.m == m:
            return True
    return False

#creates a new move, given an oldmove and two positions,
# p1 is the position to take from, p2 - add to
def createNewMove (oldmove, p1, p2):
    global n
    newmove = []
    for i in range (n):
        newmove.append(oldmove[i])
        
    newmove[p2] = newmove[p1][0:1] + newmove[p2]
    newmove[p1] = newmove[p1][1:]

    # don't allow big guy to move left
    if p2 < p1 and newmove[p2][0:1] == str(n):
        return oldmove
    else:
        return newmove

# this is the basic Breadth Order Search algorithm
# the "queue" is merely an index in the tree.                                                
def search(move):
    global n
    if done(move):
        return 0
    else:
        tree = []
        queue = 0
        tree.append(Node(move,0))          
        while queue < len(tree):   
            x = tree[queue]
            queue = queue + 1
            for i in range(n):
                # move right
                if i < n-1:
                    if len(x.m[i+1]) == 0 or x.m[i][0:1] < x.m[i+1][0:1]:
                        newmove = createNewMove(x.m, i, i+1) 
                        if not inTree(tree, newmove):
                            tree.append(Node(newmove,x.level + 1))
                            if done(newmove):
                                return x.level + 1
                # move left
                if i > 0:
                    if len(x.m[i-1]) == 0 or x.m[i][0:1] < x.m[i-1][0:1]:
                        newmove = createNewMove(x.m, i, i-1)
                        if not inTree(tree, newmove):
                            tree.append(Node(newmove,x.level + 1))
                            if done(newmove):
                                return x.level + 1
                          
        return "IMPOSSIBLE"     

n = input()
while n > 0:
    m = raw_input().strip().split()
    print search(m)
    n = input()

