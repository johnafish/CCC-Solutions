# CCC 2000 - J5 Surfin'
#
# This algoritm by Ahmed Sabie of Glenforest Secondary School
#
# This approach involves assigning each website a numerical node
# str.find used to get all the links
#
# Using the sample data given in the problem:
#
# after the initial read loop to determine who is linked to whom;
#
# nodeassign = {'http://ccc.uwaterloo.ca': 0,
#               'http://abc.def/ghi': 1,
#               'http://xxx': 3,
#               'http://www.www.www.com': 2}
# Graph =      [[False, True, False, False],
#               [False, False, True, True],
#               [False, False, False, False],
#               [False, False, False, False]]
#
# Standard BFS is used for checking for a possible surf path.
# flag is used to hold the answer.
#
# after the first query (uwaterloo to www?)
#    flag = [True, True, True, True]
# and after the second query (www to uwaterloo?)
#    flag = [False, False, True, False]

file = open ("surf.in2", "r")

nodeassign = {}
node = 0 
graph = [[False for i in xrange(100)]for i in xrange(100)] 
n = int(file.readline())

for i in range(n):
    webpage = file.readline().strip() 
    HTMLcode = ""
    HTML = ""
    while HTML != "</HTML>":
        HTML = file.readline().strip()
        HTMLcode += HTML
        
    while HTMLcode.find("A HREF=") != -1: 
        HTMLlink = HTMLcode.find("A HREF=") 
        start = HTMLcode.find('"', HTMLlink) 
        end = HTMLcode.find('"', start + 1)
        link = HTMLcode[start + 1: end]
        print "Link from", webpage, "to", link
        if webpage not in nodeassign:
            nodeassign[webpage] = node
            node += 1 
        if link not in nodeassign:
            nodeassign[link] = node
            node += 1
        graph[nodeassign[webpage]][nodeassign[link]] = True
        HTMLcode = HTMLcode[end + 1:] 

print

here = file.readline().strip() 
while here != "The End":
    there = file.readline().strip() 
    flag = [False for i in xrange(100)]
    queue = [nodeassign[here]]
    flag[nodeassign[here]] = True 
    end = nodeassign[there]
    while queue:
        u = queue.pop(0)
        for i in xrange(100): 
            if graph[u][i] and not flag[i]: 
                flag[i] = True
                queue.append(i)
    if flag[end]:
        print "Can surf from", here, "to", there 
    else:
        print "Can't surf from", here, "to", there 
               
    here = file.readline().strip() 
