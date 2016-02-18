# CCC 2011 Senior 3: Alice through the Looking Glass
# written by C. Robart
# March 2011

# Recursion. It's simple, once done, but
# it took a LONG while to think of it and debug :-)
#
# Consider the original configuration of blocks
#   _____
#   __X__
#   _XXX_
#
# As the grid expands, with increased m, I think of the blocks
# getting ever smaller, with the same shape of 4 blocks placed on the
# top of the existing blocks.
# So imagine that the above picture is of a 125x125 grid (m=3)
# each of those "blocks" is 25x25, the bottom left one starting at
# x = 25 and running to x = 49. Let's consider x = 35.
# If you divide x by 5^m-1 or 35 by 25, you get 1. More or less
# right back at the beginning. There is a block over x=1, two over x=2
# and a block over x = 3, nothing over x = 0 or 4.
# HOWEVER, and this is the point, we are in the world of m=3, so our "block"
# is really 25 high. Hence there are 1*power (25) blocks above x = 35 when m=3,
# PLUS all the blocks above that!
# The recursive call piles up the smaller blocks,
# with m-1 (=2) and the x in this new world is 10. (35 % 25 = 10).
# Draw a picture and you'll see it. :-)
#
# Back in the main pgm, if y is < the number of blocks above x,
# then x,y is IN the crystal! ged


def crystalSquaresatX(m,x):
    if m >= 1:
        power = 5 ** (m-1)
        location = x // power
        if location == 0 or location == 4:
            return 0
        elif location == 1 or location == 3:
            return 1 * power + crystalSquaresatX(m - 1, x % power)
        elif location == 2:
            return 2 * power + crystalSquaresatX(m - 1, x % power)
        return maxheightatx
    return 0


# file input
file = open("s3.1.in", 'r')
T = eval(file.readline())
for t in range(0,T):
    line = (file.readline())
    space = line.find(" ")
    m = eval(line[0:space])
    line = line[space+1:]
    space = line.find(" ")
    x = eval(line[0:space])
    y = eval(line[space+1:])
    if y < crystalSquaresatX(m,x):
        print "crystal"
    else:
        print "empty"
