# CCC 2014 Junior 3: Double Dice
#
# straight forward loop and counting
#

file = open ("j3.5.in", "r")
n = int(file.readline())

antonia = 100
david = 100

for x in range(n):
    roll = file.readline().split()
    a = int(roll[0])
    d = int(roll[1])
    if a < d:
        antonia = antonia - d
    elif d < a:
        david = david - a

print antonia
print david
