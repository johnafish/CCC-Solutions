# CCC 2011 Junior 5: Unfriend
# algorithm Daniel Cressman
#
# March 2011
#
# this is a slight variation which allows j<=i
# making it a more general solution, but somewhat unnecessary
# given the strict definition of the problem.
#
# To calculate ways(x), note that x could either remove y (that's 1 way)
# or a set of people under y (that's ways(y)), for each y that x invited.
# So that means ways(x) = the product of (1+ways(y)) for each y that x
# invited.
#
# Lets work thru an example input: 6,4,4,5,6,6     (see tree below)
#                                                           6
# so  n = 6                                              4     5
# and friends = [4,4,5,6,6]                             1 2   3
#
#    so ways(1) = 1 (1 invites no one)         
#       ways(2) = 1 (2 invites no one)    
#       ways(3) = 1 (3 invites no one)
#       ways(4) = (1+ways(1)) * (1+ways(2)) = (1+1)*(1+1) = 4
#       ways(5) = (1+ways(3)) = (1+1) = 2
#       ways(6) = (1+ways(4)) * (1+ways(5)) = (1+4)*(1+2) = 15
#
#
# this method is as described above, except:
# the friends list and n are passed because they are not global
# y is the index of the list and goes 0,1,2,...n-2
# but then when it is passed to ways(), it is needs to be 1,2,3...n-1
def ways(friends,n,x):
    answer = 1
    for y in range(n-1):
        if friends[y] == x:  # read: "x invited y"
                             # except y is really y+1 because
                             # indexes start at 0, not 1
            answer = answer * (1 + ways(friends,n,y+1))
    return answer
            
# input the info
n = input()
friends = []
for i in range(1, n):
    friends.append (input())
    
print ways(friends,n,n)



