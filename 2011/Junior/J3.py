# CCC 2011 Junior 3: Sumac Sequences
# written by C. Robart
# March 2011

# a single loop 

a = input()
b = input()

count = 2
while a >= b and a >= 0 and b >= 0:
    count = count + 1
    c = a - b
    a = b
    b = c
print count
