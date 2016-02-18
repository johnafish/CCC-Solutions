# S5 : Mouse Journey
#
# a relatively straight forward 2D array problem
#
# the idea is that you maintain the total number of moves
# from the top left corner, to ALL positions in the array
# then the bottom right element contains the answer
#
# if you had;
# 1 1 1 
# 1 2 3
# C 2 x
#
# then the value at x is 2 + 3
# because if you gat get to the cage to the left in 2 paths
# and to the cage above in 3 paths, then to this cage,
# there are 5 paths.
#
# Kevin Luo, Eric Hamber Secondary School (Vancouver)
# suggested several improvements (simlifications) to my original.
# These included
# 1. adding an extra row of 0's, top and left
#    so no special processing of the first row and column of the
#    lab is required.
# 2. Add an extra 2D array, fixed which basically says,
#    "don't change this position" (used for the original 1,1
#    and the cats) then processing is a joke:
#    just add the guy above and to the left.


file = open("s5.7.in", 'r')

# create the lab (the 2d aray of max paths to get to this cage)
x = file.readline().split()
r = eval(x[0]) 
c = eval(x[1])
lab = []
fixed = []
for i in range(r+1):
    row = []
    frow = []
    for j in range (c+1):
        row.append(0)
        frow.append(False)
    lab.append(row)
    fixed.append(frow)

# fill the lab with cats
k = eval(file.readline())
for i in range(k):
    x = file.readline().split()
    catr = eval(x[0])
    catc = eval(x[1])
    lab[catr][catc] = 0
    fixed[catr][catc] = True

# get started
lab[1][1] = 1
fixed[1][1] = True

# process the lab
for i in range(1,r+1):
    for j in range (1,c+1):
        if not fixed[i][j]:
            lab[i][j] = lab[i-1][j] + lab[i][j-1]

print lab[r][c]

