# CCC 2013 Junior 2: Rotating Letters

# Does a given string consist of only "rotatable" letters?
# (IOSHZXN)

word = raw_input()
rotate = "IOSHZXN"
answer = "YES"
for letter in word:
    if letter not in rotate:
        answer = "NO"
print answer
