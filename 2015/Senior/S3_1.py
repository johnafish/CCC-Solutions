# CCC 2015 Senior 3: Gates
#
# This solution is by Matthew Lee of Galt C.V.I.
#
# Here is his explaination:
#   The way it works is that I create an array for the total amount of open spots,
#   Then as each airplane tries to land,
#      it starts off at its highest possible spot number.
#      If the number is not 0, it jumps back the amount of places that number says and
#      then adds 1 to that spot. If spot 10 has been accessed 8 times,
#            it is known that the previous 8 have been taken.

data = open("s3.10.in")

ports = int(data.readline())
numPlanes = int(data.readline())

planes = []

total = 0

for i in range(ports + 1):
    planes.append(0)

i = 0
keepGoing = True
while i < numPlanes and keepGoing:
    cPlane = int(data.readline())
    while cPlane > 0 and planes[cPlane] > 0:
        t = planes[cPlane]
        planes[cPlane] = planes[cPlane] + 1
        cPlane = cPlane - t
    if cPlane <= 0:
        keepGoing = False
    else:
        planes[cPlane] = 1
        total = total + 1
    i = i + 1

print total 
