# CCC Senior 3: the Geneva Confection
#
# A Stack problem
#
# Test case 5 runs on my machine in 4 seconds :-)
# (a Pentium 4, 3.2 Ghz, 1GB RAM)
#
# I fine tuned the file input to shave off a second or 2.
# (File input seems to be the slowest part.)
#
# For the cars on the mountain, I just use an index (mtnCar)
# to keep track of where I am.
# (Nothing actually is removed from it.)
#
# For the branch, I use a list and literally add to or
# pop from it. (You can't just keep track of where you are,
# because sometimes things have to 'come off' literally.)
#

file = open ("s3.5.in", "r")
entirefile = file.readlines()
p = 0
t = int(entirefile[p])
p = p + 1

for test in range(t):
    n = int(entirefile[p])
    p = p + 1
    mountain = []
    for i in range(n):
        mountain.append(int(entirefile[p]))
        p = p + 1

    branch = []
    mtnCar = n - 1

    nextCar = 1
    state = "Y"

    # Next car should be either on mountian or branch
    # if not, move a car from mountian into branch

    while state == "Y" and nextCar <= n:
        # if the next car is on the mountain, take it off
        if mtnCar >= 0 and nextCar == mountain[mtnCar]:
            mtnCar = mtnCar - 1
            nextCar = nextCar + 1
        # if the nextcar is on the branch, take it off
        elif len(branch) > 0 and nextCar == branch[len(branch) - 1]:
            branch.pop(len(branch) - 1)
            nextCar = nextCar + 1
        # otherwise move a car from the mountain to the branch
        # (if you can)    
        elif mtnCar >= 0:
            branch.append(mountain[mtnCar])
            mtnCar = mtnCar - 1
        # otherwise you're done
        else:
            state = "N"

    print state
            
 
