# S2 2012: Aromatic Numbers
#
# straight forward Roman Numeral type
# character manipulation problem.


# converts the letter "X", "L", etc into the
# equivalent value
def R (x):
    if x == 'I':
        return 1
    elif x == 'V':
        return 5
    elif x == 'X':
        return 10
    elif x == 'L':
        return 50
    elif x == 'C':
        return 100
    elif x == 'D':
        return 500
    else:
        return 1000

file = open("s2.2.in", 'r')
aromatic = file.readline().strip()
oldr = 999999
olds = 0
value = 0
for i in range(0, len(aromatic), 2):
    a = eval(aromatic[i:i+1])
    r = R(aromatic[i+1:i+2])
    s = a * r
    value = value + s
    if r > oldr:
        value = value - 2 * olds
    olds = s
    oldr = r

print value
          

