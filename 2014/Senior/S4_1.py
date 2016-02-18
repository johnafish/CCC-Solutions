# CCC 2014 S4: Tinted Glass Window
#
# This code is by Calvin Liu of Glenforest Secondary School
# (Yikuan (Timothy) Li also suggested a similar approach.) 
#
# This algorithm uses a "Sweep-Line".
# I will let Calvin explain:
#   This performs coordinate compression on the Y-axis 
#   [that means only using the y coordinates of the rectangles]
#   and runs a sweep line along the X-axis.
#   Every rectangle is separated into two vertical line segments,
#   an incoming edge from the left and an outgoing edge to the right.
#   The value of a rectangle is added when the sweep line encounters
#   the incoming edge and subtracted when it encounters the outgoing edge.
#   The final answer accumulates through the process.
#
# Here is a trace of Calvin's program, with the example given with the question 
#  line= [-1000000000, [11, 11, 15, 1], [12, 12, 13, 1], [13, 8, 17, 2],
#                      [14, 8, 17, -2], [17, 8, 17, 1], [18, 8, 17, -1],
#                      [19, 12, 13, -1], [20, 11, 15, -1]]
#
#  After removing duplicates and sorting:
#  segy= [-1000000000, 8, 11, 12, 13, 15, 17]
#
#  coordinate compression and hash table creation
#  findy= {8: 1, 11: 2, 12: 3, 13: 4, 15: 5, 17: 6}
#
#  initialize the yaxis (keeps track of the total tint)
#  yaxis= [0, 0, 0, 0, 0, 0, 0, 0]
#
#  in the main loop
#    i= 1    yaxis= [0, 0, 1, 1, 1, 0, 0, 0]
#    i= 2    yaxis= [0, 0, 1, 2, 1, 0, 0, 0]
#    i= 3    yaxis= [0, 2, 3, 4, 3, 2, 0, 0]
#    i= 4    j= 2    ans= 1
#    i= 4    j= 3    ans= 2
#    i= 4    j= 4    ans= 4
#    i= 4    yaxis= [0, 0, 1, 2, 1, 0, 0, 0]
#    i= 5    yaxis= [0, 1, 2, 3, 2, 1, 0, 0]
#    i= 6    j= 3    ans= 5
#    i= 6    yaxis= [0, 0, 1, 2, 1, 0, 0, 0]
#    i= 7    yaxis= [0, 0, 1, 1, 1, 0, 0, 0]
#    i= 8    yaxis= [0, 0, 0, 0, 0, 0, 0, 0]
#
# technically test data set 9 runs in 6.7 seconds and
# test data set 10 runs in 5.7 seconds on my old Pentium 4 with 3.2 GHZ CPU


file = open ("s4.15.in", "r")
n = int(file.readline())
t = int(file.readline())

#-1000000000 is small enough to be at index 0 after sorting
line = [-1000000000]
#-1000000000 is just a place holder
segy = [-1000000000]   

for i in xrange(n):
    x1,y1,x2,y2,val=map(int,file.readline().split())
    line.append([x1,y1,y2,val])        #Incoming side
    line.append([x2,y1,y2,-val])       #Outgoing side
    segy.append(y1)
    segy.append(y2)
    
line.sort()

#Remove duplicates and sort
segy = list(set(segy))    
segy.sort()

#Coordinate Compression with a hash table
findy = {}     
for i in xrange(1, len(segy)):
    findy[segy[i]] = i
    
yaxis=[0 for i in xrange(len(segy) + 1)]

ans=0

#Sweep Line loop
for i in xrange(1, len(line)):         
    for j in xrange(1, len(segy)):
        if yaxis[j] >= t:
            ans += (segy[j + 1] - segy[j]) * (line[i][0] - line[i - 1][0])
    for j in xrange(findy[line[i][1]], findy[line[i][2]]):
        yaxis[j] += line[i][3]
        
print ans
