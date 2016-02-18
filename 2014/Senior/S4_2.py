# CCC 2014 S4: Tinted Glass Window
#
# This code is by Zihao Zhang of 
# (Others also suggested similar approaches including:
#    Amlesh Jayakumar, Calvin Liu and Alex Tung.) 
#
# This is the "Difference Arrays" approach:
#
# Calvin Liu explains: the value for each rectangle is
# processed through a 2D difference array,
# an extension of the data structure used for CCC 2009 S5 Wireless.
# An explanation of difference array can be found here:
# http://wcipeg.com/wiki/Difference_array
#
# Amlesh Jayakumar explains further:
# The idea is that instead of stepping through the rectangles
# and filling them up in your array, you add the tint factor
# to the left edge of the rectangle, and subtract the tint factor
# from just past the right edge of the rectangle.
# Once you've done this for all rectangles,
# you merely step through each row, adding the values
# you see in your 'difference array height map'
# (i.e. adding the tint factors of rectangles you've stepped into,
# and subtracting tint factors of rectangles you've stepped out of).
# Now whenever you're tint factor is > T,
# you add the area of the current square you're in
# (essentially (delta y)*(delta x) of the coordinates that make up
# the square you're in) to your final answer.
#
# Here is a trace using the sample data given in the question:
#
# Original input data
# rects= [[11, 11, 20, 15, 1],
#         [13, 8, 14, 17, 2],
#         [17, 8, 18, 17, 1],
#         [12, 12, 19, 13, 1]]
#
# original x and y values
# xs= [11, 20, 13, 14, 17, 18, 12, 19]
# ys= [11, 15, 8, 17, 8, 17, 12, 13]
#
# x and y values sorted with duplicates removed
# itox= [11, 12, 13, 14, 17, 18, 19, 20]
# itoy= [8, 11, 12, 13, 15, 17]
#
# a quick way to find those coordinates
# xtoi= {11: 0, 12: 1, 13: 2, 14: 3, 17: 4, 18: 5, 19: 6, 20: 7}
# ytoi= {8: 0, 11: 1, 12: 2, 13: 3, 15: 4, 17: 5}
#
# The original Difference Array is all, zeros
#
# then after adding the left side and subtracting the right
# the Difference Array becomes:
# da= [[0, 0, 2, -2, 1, -1, 0, 0],
#      [1, 0, 2, -2, 1, -1, 0, -1],
#      [1, 1, 2, -2, 1, -1, -1, -1],
#      [1, 0, 2, -2, 1, -1, 0, -1],
#      [0, 0, 2, -2, 1, -1, 0, 0],
#      [0, 0, 0, 0, 0, 0, 0, 0]]
#
# After the final "expand" step there are the "real values"
# and you get the area. :-)
# (Don't forget, each number may represent much more than a single unit area
#  so you need the multiplication.)
# da= [[0, 0, 2, 0, 1, 0, 0, 0],
#      [1, 1, 3, 1, 2, 1, 1, 0],
#      [1, 2, 4, 2, 3, 2, 1, 0],
#      [1, 1, 3, 1, 2, 1, 1, 0],
#      [0, 0, 2, 0, 1, 0, 0, 0],
#      [0, 0, 0, 0, 0, 0, 0, 0]]
#
# Technically both test data sets 9 and 10 exceed the time limit.
# They each about 9 sec on my Pentium 4, 3.2 Ghz CPU machine. 
# 

file = open ("s4.10.in", "r")
rects = []
xs = []
ys = []

n = int(file.readline())
t = int(file.readline())

for i in range(n):
	rect = list(map(int,file.readline().split()))
	xs.append(rect[0])
	ys.append(rect[1])
	xs.append(rect[2])
	ys.append(rect[3])
	rects.append(rect)

itox = sorted(list(set(xs)))
itoy = sorted(list(set(ys)))

xtoi = {itox[i]:i for i in range(len(itox))}
ytoi = {itoy[i]:i for i in range(len(itoy))}

da = [[0]*len(itox) for i in range(len(itoy))]

for i in range(n):
	for j in range(ytoi[rects[i][1]],ytoi[rects[i][3]]):
		da[j][xtoi[rects[i][0]]] += rects[i][4]
		da[j][xtoi[rects[i][2]]] -= rects[i][4]

ans = 0
for i in range(len(itoy)-1):
	for j in range(len(itox)-1):
		da[i][j+1] = da[i][j] + da[i][j+1]
		if da[i][j] >= t:
			ans += (itoy[i+1] - itoy[i]) * (itox[j+1] - itox[j])
				
print ans
