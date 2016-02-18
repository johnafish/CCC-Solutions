# CCC 2013 Senior 5: Factor Solitaire
#
# The method is RIDICULOUSLY simple. Work BACKWARDS from the number.
#
# How did I think of this?
#
# The straight forward method of factoring completely
# all the numbers up to 5,000,000 is simply too slow.
#
# I studied the examples given. The numbers along the way to
# the solution looked totally random. Why choose those ones?
# There was no obvious answer. Eg why go from 60 to 61 at a huge cost
# when one was so far from the end point?
#
# I used my first program and printed the minimum cost for all #s up to 100.
# Very quickly I noticed that the powers of 2 were VERY cheap. So too were
# the numbers between them. Could I use some sort of modified binary search
# system to get to my number? A few example cases proved this didn't work.
#
# I returned to the 2013 example. I noticed the when I factored the
# last number (2013 = 3 * 671) I got numbers that were added to get
# from 1342 to 2013. Why would that even work?
# If you factor a number, SUBTRACT one of the factors, what guarantee was
# there that you'd end up on a number that also had those factors? (So you
# could add them and get back to the first.) That was NOT at all obvious
# to me. That's why my score on the contest would be about 30 and not 75 :-)
#
# Okay, so 2013 has a factor of 671, what makes you think that the number
# (1342 = 2013-671) would also have a factor of 671? (and even if this was,
# why would this be the minimum cost route to the number?)
#
# Answer: multiplication means repeated addition!
# Since 2013 = 3 * 671, if you subtract 671 you are left with 2*671 or 1342.
# This DOES have the same factor (671)! AND you get it at a minimum cost.
#
# Repeat the process till you're back to 1 and QED!
#
# The only fly in the ointment is when you hit a prime....
# You can't go back from there as there are no factors, so subtract 1
# (at a huge cost) and then keep going.
#
# Here'a a trace for N = 15
#
#   N  factors  what_to_subtract newN  newN_factors      cost
#  15   3 * 5         5           10      2 * 5           2
#  10   2 * 5         5            5      1 * 5         2+1=3
#   5   prime!        1            4      4 * 1         3+4=7
#   4   2 * 2         2            2      1 * 2         7+1=8
#   2   prime!        1            1      1 * 1         8=1=9
#
# min (15) = 9
#
# I'm still fuzzy as to why this does give the minimum cost overall, but
# IT WORKS, so please do NOT tell me why, I like the mystery and as a CS
# person, all I really care about is HOW to make it work, not WHY. :-)


import math

file = open("s5.15.in", 'r')
N = int(file.readline().strip())

cost = 0
while N > 1:
    # find the first pair of factors of N (eg for 24 it's 2*12)
    r = int(math.sqrt(N)) + 1
    f = 2
    while f <= r and N % f != 0: 
        f = f + 1    

    if f < N and N % f == 0: # its a real factor and N is not prime
        x = N / f
        N = N - x
        cost = cost + N / x 
    else:                    # if N is prime, go back only 1
        N = N - 1
        cost = cost + N
print cost
