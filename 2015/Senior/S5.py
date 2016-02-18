# CCC 2015 Senior 5: Greedy for Pies
#
# by Daniel Whitney, Newmarket High School
#
# NOTE: This is in python 3, tho I believe it would take very little
#       to convert it to python 2 (switch range to xrange, print() to
#       print).
# NOTE: This is way over 5 seconds for the 5 M = 100 if the default
#       python interpreter (cPython) is used, but if the JIT python
#       interpreter (PyPy) is used, the longest problems finish in
#       under 3 seconds :), i don't know if that counts tho, but it
#       was legal to use PyPy in the competition this year.
#
# This problem is a dynamic programming problem, so to solve it one
# must identify a way to store the states of the problem (sub problems)
# and identify the formula to find the optimal solution to that problem.
#
# First we have to make a few observations:
#  1. There is no point in inserting two pies directly adjacent to one
#     another, as if you do you could move that set to the end of the
#     list and it would have the same effect on the total.  Give it a try
#     and you'll see it is true.
#  2. It does not matter the specific order of the inserted pies, only
#     if a pie is counted or not.  If pies are counted their order could
#     be changed with no adverse effects.  Thus if we sort the pies we
#     can insert, we can either take pies from the bottom (pies that wont
#     count) or the top (pies that will count) of the list.
#
# Now, first look at the simpler problem, finding the maximum sugar
# without inserting pies.  We could identify a state as including all the
# pies up to a point (for example the first 5 pies).  We define two
# functions, one function will include the last pie on the list, the other
# will not.  I'll call these function I and E (include and exclude).  We
# can deduce that the value of I for a number of pies n, will be the
# maximum of E of n-1 plus the value if the nth pie or I of n-1. The
# solution is thus the maximum of I of number of pies or E of number of
# pies.
#
# For this problem we have to expand the state to include the inserted
# pies, so we add three more varables to the state, m for number of pies
# taken from the bottom of the sorted list of pies to add (they wont add
# to the total), M for number of pies taken from the top of the sorted
# list of pies to add (they will add to the total), and l to indicate if
# the last pie added was from the original list, or the new list.  l = 0
# indicates last item taken from original list, l = 1 indicates last item
# taken from the insertion list.  Therefore if the I function takes from
# the new list it must take from the top of the list as the item will be
# included,and vise versa for the E function.
#
# Also we must ignore any state with a negative n, m, or M as they don't
# exist.
#
# Also note the base states for this function are simple, the state of
# just one item taken from the original list, or just one item from the
# top or bottom of the list of pies to be inserted.
#
# The new formulas are as follows:
#   I of n, m, M, l = the maximum of
#       E of n, m-1, M, 1   if  l is = 0 because we cant have adjacent inserted pies
#       E of n-1, m, M, 0   if  m+M != n+1 because if we remove an item from the original list
#                                          the remainder would have two more inserted items than
#                                          original items which would indicate adjacent insertions.
#
#       also if the last item inserted is from the new list (l = 1) and the state has the maximum
#       number of pies from the inserted list, then this state cannot exist and is skipped.
#
#       finally you would add the value of the added pie as the I function includes the last pie
#
#   E of n, m, M, l = the maximum of
#       E of n, m-1, M, 1 or
#       I of n, m, M-1, 1   if  l is = 0 because we cant have adjacent inserted pies
#       I of n-1, m, M, 0
#       E of n-1, m, M, 0   if  m+M != n+1 because if we remove an item from the original list
#                                          the remainder would have two more inserted items than
#                                          original items which would indicate adjacent insertions.
#
#       if the last item inserted is from the new list (l = 1) and the state has the maximum
#       number of pies from the inserted list, then this state cannot exist and is skipped.
#
#       the value of the last pie is not added as the E function excluded the last pie
#
# We can calculate the values of these states from the bottom up (or
# iteratively) as the value of one function only relies on the values
# of the functions below it (with numbers lass than or equal to it).
#
# There is one final messy step, we must iterate through every state that
# contains all of the original list, and append to the end the optimal
# ordering of the remaining pies we could add.  This is simple, for an E
# function we simply add the sum of the upper half of the list of remaining
# pies to the list, and include the center pie if there's is an odd number
# of pies, and exclude this center pie for the I functions.  We then return
# the maximum sums of all these lists.


file = open('s5.15.in')
N = int(file.readline())
L = [int(file.readline()) for n in range(N)] + [0]  # read the ordered list of pies into a list
P = int(file.readline())
A = [int(file.readline()) for m in range(P)]        # read the pies to be inserted into a list and sort it
A.sort()

# Because of the versatile but slow nature of python's lists, I condensed
# what was a 5D array (first index was 0 or 1 to indicate I or E,
# second was n, third was m, etc.) but it took longer to initialize the
# list than actually calculate the states.  Using one list and multiplying
# the indexes by fixed numbers to simulate a multidimensional array was
# more than 6x faster.

# these are the factors to multiply the indices by
s5 = 1
s4 = 2*s5
s3 = (P+1)*s4
s2 = (P+1)*s3
s1 = (N+1)*s2

# this will hold all of the states
s = [0]*(2*s1)

# these are the bases states
s[0] = L[0]
if P != 0:
    s[1] = A[-1]

# loop through every possible valid state, in the order were all of the prerequisite states are already calculated.
for n in range(N+1):
    for m in range(min(n+1, P)+1):
        for M in range(min(n+1, P)+1-m):
            # we calculate the value of I and E for a state at the same
            #  time, first when the last item is from the original list
            max_i = 0
            max_e = 0
            # include a case only if the case is valid
            # include states where the last pie is from the insertion list
            if m > 0:
                max_i = s[1*s1 + n*s2 + (m-1)*s3 + M*s4 + 1]
                max_e = s[1*s1 + n*s2 + (m-1)*s3 + M*s4 + 1]
            if M > 0:
                max_e = max(s[n*s2 + m*s3 + (M-1)*s4 + 1], max_e)
            # include states where the last pie is from the original list
            if M+m != n+1 and n > 0:
                max_i = max(s[1*s1 + (n-1)*s2 + m*s3 + M*s4], max_i)
                max_e = max(s[1*s1 + (n-1)*s2 + m*s3 + M*s4], s[(n-1)*s2 + m*s3 + M*s4], max_e)
            # calculate the value of the I function
            s[n*s2 + m*s3 + M*s4] = max_i + L[n]
            # calculate the value of the E function
            s[1*s1 + n*s2 + m*s3 + M*s4] = max_e
            # now calculate the value of the functions when the last item is from the insertion list
            if M+m != P and M+m != n+1 and n > 0:
                # if the last item inserted is from the new list (l = 1) and the state has the maximum
                # number of pies from the inserted list, then this state cannot exist and is skipped
                # or if the number of inserted pies equals the total number of inserted pies it is skipped
                # include the cases where the last pie is from the original case if the case is valid
                max_i = s[1*s1 + (n-1)*s2 + m*s3 + M*s4]
                max_e = max(s[1*s1 + (n-1)*s2 + m*s3 + M*s4], s[(n-1)*s2 + m*s3 + M*s4])
                s[n*s2 + m*s3 + M*s4 + 1] = max_i + A[-M-1]
                s[1*s1 + n*s2 + m*s3 + M*s4 + 1] = max_e

# finally add the sums of the leftover pies
final_max = 0
for m in range(min(N+1, P)+1):
    for M in range(min(N+1, P)+1-m):
        # rA is all the pies we didn't add to the list
        if M == 0:
            rA = A[m:]
        else:
            rA = A[m:-M]
        final_max = max(final_max,
                        s[1*s1+(N-1)*s2+m*s3+M*s4] + sum(rA[len(rA)//2:]),
                        s[(N-1)*s2+m*s3+M*s4] + sum(rA[-((-len(rA))//2):]))
                                    # this strange statement causes upward rounding as opposed to downwards
        if M+m != P or M+m != N+1:
            if M == 0:
                rA = A[m+1:]
            else:
                rA = A[m+1:-M]
            final_max = max(final_max, s[1*s1+n*s2+m*s3+M*s4+1] + sum(rA[len(rA)//2:]))
            rA = A[m:-M-1]
            final_max = max(final_max, s[n*s2+m*s3+M*s4+1] + sum(rA[-((-len(rA))//2):]))

print(final_max)
