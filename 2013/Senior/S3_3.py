# CCC 2013 Senior 3: Chances of Winning
#
# by Johnson Chen (Nepean High School)
#
# this is a straight forward, brute force method
# but cleaner than mine :-)
#
# teamcomp is a 2D array. A 1 at position i,j indicates
# that i has NOT YET played j.
#
# For example the original values in teamcomp are:
#     0 1 1 1
#     0 0 1 1
#     0 0 0 1
#     0 0 0 0
#
# and after input of:
#  3
#  4
#  1 3 5 7
#  3 4 8 0
#  2 4 2 2
#  1 2 5 5
#
# then teamcomp would look like:
#     0 0 0 1
#     0 0 1 0
#     0 0 0 0
#     0 0 0 0
#
# Sum would have in it [1,2,6,1] after the above input.
#
# the big nested loops go thru all the games left and
# calculate their new sums.
# Final counts the number of wins for the given team. 
#

file = open("s3.7.in", 'r')
team = int(file.readline()) - 1
n = int(file.readline())

a = []
b = []
ascore = []
bscore = []          
for i in range(n):
    x = file.readline().strip().split()
    a.append(int(x[0]) - 1)
    b.append(int(x[1]) - 1)
    ascore.append(int(x[2]) - 1)
    bscore.append(int(x[3]) - 1)

sum = [0,0,0,0]
teamcomp = [[0,1,1,1],[0,0,1,1],[0,0,0,1],[0,0,0,0]]

# if (teamcomp[i][j]==1), i hasn't played with j yet
for i in range(n):
    teamcomp[a[i]][b[i]] = 0
    teamcomp[b[i]][a[i]] = 0

#each team's score so far
for i in range(n):
    if ascore[i] > bscore[i]:
        sum[a[i]] = sum[a[i]] + 3
    elif ascore[i] == bscore[i]:
        sum[a[i]] = sum[a[i]] + 1
	sum[b[i]] = sum[b[i]] + 1
    else:
        sum[b[i]] = sum[b[i]] + 3

one = 1
two = 1
three = 1
four = 1
five = 1
six = 1
final = 0
answer = 0
pt = [0,1,3]
newsum = [0,0,0,0]

l = 0
while l < 3:
    if teamcomp[0][1] == 0:
        one = 0
        l = 2
    m = 0
    while m < 3:
        if teamcomp[0][2] == 0:
            two = 0
            m = 3
        p = 0
        while p < 3:
            if teamcomp[0][3]==0:
                three = 0
                p = 3
            q = 0
            while q < 3:
                if teamcomp[1][2] == 0:
                    four = 0
                    q = 3
                r = 0
                while r < 3:
                    if teamcomp[1][3] == 0:
                        five = 0
                        r = 3
                    s = 0
                    while s < 3:
                        if teamcomp[2][3] == 0:
                            six = 0
                            s = 3
                        newsum[0] = sum[0] + pt[l*one] + pt[m*two] + pt[p*three]
                        newsum[1] = sum[1] + pt[(2-l)*one] + pt[q*four] + pt[r*five]
                        newsum[2] = sum[2] + pt[(2-m)*two] + pt[(2-q)*four] + pt[s*six]             
                        newsum[3] = sum[3] + pt[(2-p)*three] + pt[(2-r)*five] + pt[(2-s)*six]
                        win = True;
                        for i in range(4):
                            if newsum[team] <= newsum[i] and team != i:
                                win = False
                        if win:
                            final = final + 1
                        s = s + 1
                    r = r + 1
                q = q + 1
            p = p + 1
        m = m + 1
    l = l + 1

print final

