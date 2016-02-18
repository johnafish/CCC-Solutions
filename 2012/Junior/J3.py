# J3 2012: Icon Scaling!
#
# simple loops.
#

k = input()
star = ""
x = ""
space = ""
for i in range(k):
    star = star + "*"
    x = x + "X"
    space = space + " "

for i in range (k):
    print star+x+star
for i in range (k):
    print space+x+x
for i in range (k):
    print star+space+star

    
    
