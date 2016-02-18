# CCC 2015 Senior 1: Zero That Out
#
# A list processing problem (using a stack)
#
# Python's append() - add to the end of the list
#      and pop() - remove the last item
# make this an easy problem


file = open("s1.5.in", "r")
k = int(file.readline())

list = []
for i in range(k):
    n = int(file.readline())
    if n > 0:
        list.append(n)
    else:
        list.pop()

sum = 0
for i in range(len(list)):
    sum = sum + list[i]

print sum
