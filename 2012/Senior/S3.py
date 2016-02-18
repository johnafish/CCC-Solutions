# S3 2012: Absolutely Acidic
#
# For the largest files this takes about 45sec.
# All that is spent reading. The sort takes
# less than 1 second. 
#
# The algorithm is very straight forward;
#  read the file and count the readings and store in a list of 1..1000
#  sort the list descending (sub sorting helps case 1)
#  max and second max are at the first so determine the case
#  and calculate the answer. 

class Info:
    def __init__(self, r, c):
        self.reading = r
        self.count = c
        

file = open("s3.5.in", 'r')

# initialize the list
freq = []
for i in range(1001):
    freq.append(Info(i,0))

# read the file and add the frequencies
# need to skip the first line.
firstline = True
for line in file:
    if firstline:
        firstline = False
    else:
        r = eval(line)
        freq[r].count = freq[r].count + 1

# sort freq, by count, subsorted by the reading
# use insertion sort method
for i in range (1,1001):
    hold = freq[i]
    j = i - 1
    while j >= 0 and (freq[j].count < hold.count or
                      (freq[j].count == hold.count and
                       freq[j].reading < hold.reading)):
        freq[j+1] = freq[j]
        j = j - 1
    freq[j+1] = hold    

# determine the type
multihigh = freq[0].count == freq[2].count
multisecondhigh = freq[0].count != freq[1].count and freq[1].count == freq[2].count
        
# get the answer
if multihigh:
    i = 1
    while i < 1001 and freq[0].count == freq[i].count:
        i = i + 1 
    print abs(freq[0].reading - freq[i-1].reading)
elif multisecondhigh:
    i = 3
    m = abs(freq[0].reading - freq[1].reading)
    while i < 1001 and freq[1].count == freq[i].count:
        m = max (m, abs(freq[0].reading - freq[i].reading))
        i = i + 1 
    print m
else:
    print abs(freq[0].reading - freq[1].reading)
                      
