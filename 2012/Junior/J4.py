# J4 2012: Big Bang Secrets
#
# straight forward character manipulation
# in a classic substitution cipher.
#
# the shift = 3 * position + k
#

k = input()
cipher = raw_input()

plain = ""
for i in range(len(cipher)):
    letter = cipher[i:i+1]
    shift = 3 * (i+1) + k
    x = ord(letter) - shift
    if x < ord("A"):
        x = ord("Z") + x - ord("A") + 1
    plain = plain + chr(x)

print plain
    
