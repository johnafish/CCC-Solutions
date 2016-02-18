# CCC 2011 Senior 5: Switch
# written by C. Robart
# March 2011

# this is a string exercise. Starting from either end find the
# smaller group of 1's. Then turn on a single light next to that group
# on the side closest to the centre. If groups of 4 or more are
# created, switch them off. Repeat. Done. 


# do the switch, turn all groups of 7,6,5 or 4 lights off.
# (can't be 8 on together as no group of 4 exists by itself)
def switchOff(x):
    x = x.replace ("1111111","0000000")
    x = x.replace ("111111","000000")
    x = x.replace ("11111","00000")
    x = x.replace ("1111","0000")
    return x

# returns true if there are no lights on
def done(x):
    return x.find("1") < 0


# this will turn on one light
# working from the ends, it finds the first/last group of lights
# it then turns on a light next to the smallest of these two groups,
# towards the centre.
#
# a group, coming from the left, looks like "10"
# then count the 1's previous to this.
#
# a group, coming from the right, looks like "01"
# then count the 1's previous to this.
#
# comparing the two above, turn on the light, towards the centre.
#
def turnOnNext(x):
    left = x.find("10")
    if left >= 0:
        leftPosition = left + 1
        leftSize = 0
        while left >= 0 and x[left] == "1":
            left = left - 1
            leftSize = leftSize + 1
    else:
        leftPosition = -1
        leftSize = 99999

    right = x.rfind("01")
    if right > 0:
        rightPosition = right
        rightSize = 0
        right = right + 1
        while right < len(x) and x[right] == "1":
            right = right + 1
            rightSize = rightSize + 1
    else:
        rightPosition = -1
        rightSize = 99999
    if leftPosition > 0:
        if leftSize <= rightSize:
            x = x[0:leftPosition] +  "1" + x[leftPosition+1:]
        else:
            x = x[0:rightPosition] +  "1" + x[rightPosition+1:]
    else:
        x = x[0:rightPosition] +  "1" + x[rightPosition+1:]
    return x
    
# file input
file = open("s5.1.in", 'r')
K = eval(file.readline())
lights = ""
for i in range(0,K):
    lights = lights + str(eval(file.readline()))

count = 0
while not done(lights):
    lights = turnOnNext(lights)
    count = count + 1
    lights = switchOff(lights)
    
print count
