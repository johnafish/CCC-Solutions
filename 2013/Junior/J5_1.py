# CCC 2013 Junior 5: Chances of Winning
#
# there are 6 games: 1-2, 1-3, 1-4, 2-3, 2-4, 3-4
# (or using 0 thru 3: 0-1, 0-2, 0-3, 1-2, 1-3, 2-3)
# each game is either a "W", "L", "T" wrt the 1st team.
# thus each combo of games could be represented as something like "WLLTWT" 
#
# With no games played, set the original string to "------",
# or after a few games, set it to something like "-W--L-"
#
# Using that original string create a list of all other possible games.
#
# Count those strings that win for T.


# given a string (s) such as "WLTTLW" and a t determine if
# s results in a winning combo for t (0-3).
def winning(s, t):
    score = [0,0,0,0]
    if s[0] == "W":
        score[0] = score[0] + 3
        score[1] = score[1] + 0
    elif s[0] == "L":
        score[0] = score[0] + 0
        score[1] = score[1] + 3
    else:
        score[0] = score[0] + 1
        score[1] = score[1] + 1
    if s[1] == "W":
        score[0] = score[0] + 3
        score[2] = score[2] + 0
    elif s[1] == "L":
        score[0] = score[0] + 0
        score[2] = score[2] + 3
    else:
        score[0] = score[0] + 1
        score[2] = score[2] + 1
    if s[2] == "W":
        score[0] = score[0] + 3
        score[3] = score[3] + 0
    elif s[2] == "L":
        score[0] = score[0] + 0
        score[3] = score[3] + 3
    else:
        score[0] = score[0] + 1
        score[3] = score[3] + 1
    if s[3] == "W":
        score[1] = score[1] + 3
        score[2] = score[2] + 0
    elif s[3] == "L":
        score[1] = score[1] + 0
        score[2] = score[2] + 3
    else:
        score[1] = score[1] + 1
        score[2] = score[2] + 1
    if s[4] == "W":
        score[1] = score[1] + 3
        score[3] = score[3] + 0
    elif s[4] == "L":
        score[1] = score[1] + 0
        score[3] = score[3] + 3
    else:
        score[1] = score[1] + 1
        score[3] = score[3] + 1
    if s[5] == "W":
        score[2] = score[2] + 3
        score[3] = score[3] + 0
    elif s[5] == "L":
        score[2] = score[2] + 0
        score[3] = score[3] + 3
    else:
        score[2] = score[2] + 1
        score[3] = score[3] + 1
    if score[t] == max(score) and score.count(max(score)) == 1:
        return True
    else:
        return False

t = input() - 1
g = input()

original = "------"
for i in range(g):
    x = raw_input().strip().split()
    a = int(x[0]) - 1
    b = int(x[1]) - 1
    sa = x[2]
    sb = x[3]
    if sa > sb:
        letter = "W" 
    elif sa < sb:
        letter = "L" 
    else:
        letter = "T" 
    if a == 0 and b == 1:
        original = letter + original[1:]
    elif a == 0 and b == 2:
        original = original[0] + letter + original[2:]
    elif a == 0 and b == 3:
        original = original[:2] + letter + original[3:]
    elif a == 1 and b == 2:
        original = original[:3] + letter + original[4:]
    elif a == 1 and b == 3:
        original = original[:4] + letter + original[5]
    elif a == 2 and b == 3:
        original = original[:5] + letter 

choice = "WLT"
possible = []

goto = []
for i in range(6):
    if original[i] == "-":
        goto.append(3)
    else:
        goto.append(1)

for a in range(goto[0]):
    for b in range(goto[1]):
        for c in range(goto[2]):
            for d in range(goto[3]):
                for e in range(goto[4]):
                    for f in range(goto[5]):
                        s = ""
                        if goto[0] == 1:
                            s = s + original[0]
                        else:
                            s = s + choice[a]
                        if goto[1] == 1:
                            s = s + original[1]
                        else:
                            s = s + choice[b]
                        if goto[2] == 1:
                            s = s + original[2]
                        else:
                            s = s + choice[c]
                        if goto[3] == 1:
                            s = s + original[3]
                        else:
                            s = s + choice[d]
                        if goto[4] == 1:
                            s = s + original[4]
                        else:
                            s = s + choice[e]
                        if goto[5] == 1:
                            s = s + original[5]
                        else:
                            s = s + choice[f]
                        possible.append(s)

count = 0
for s in possible:
    if winning(s, t):
        count = count + 1

print count

