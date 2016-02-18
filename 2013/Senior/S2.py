# CCC 2013 S2: Bridge Transport
#
# This solution is due largely to Nenad Bauk
# (also thanks to Victor Wang of Tecumseh Elementary
# re: what if the last car makes the bridge collapse?
# This is NOT tested in the test data, but is the example given.
# This program passed this test :-), as well as the 13 test cases.)
#
# Generally speaking one wishes to calculate the weight of four cars.
# The weights are stored in an array (list in python).
#
# Nenad's insight is to simplify things by starting the array with 3
# zero weight cars. This way there are no special cases to consider
# wrt the first three cars. There are always four cars to add up:
# the current one and the 3 previous!

# To simplify issues around the last car, I also added a sentinel to
# the end, a car with a weight in excess of the max. 


#input the data
file = open("s2.0.in", 'r')
W = int(file.readline())
N = int(file.readline())
carWeight = [0,0,0]
for i in range(N):
    carWeight.append(int(file.readline()))
carWeight.append (W+1)

# calculate the weight of each group of four cars
# stopping when the max weight is exceeded.
# the senteniel guarantees I'll never fall off the end of the array
# and thus no need to check for that.

carsAcross = 0
i = 3
totalWeight = carWeight[i-3] + carWeight[i-2] + carWeight[i-1] + carWeight[i]
while totalWeight <= W:
    carsAcross = carsAcross + 1
    i = i + 1
    totalWeight = carWeight[i-3] + carWeight[i-2] + carWeight[i-1] + carWeight[i]
print carsAcross
