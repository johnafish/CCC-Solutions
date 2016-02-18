# CCC 2015 Junior 3: Rovarspraket
#
# Strings (or arrays) will do this one
# though I suppose a monster if would work too :-)
#

file = open("j3.5.in", "r")
word = file.readline()

consonant =     "bcdfghjklmnpqrstvwxyz"
closestVowel =  "aaeeeiiiiooooouuuuuuu"
nextConsonant = "cdfghjklmnpqrstvwxyzz"

newWord = ""
for i in range(len(word)):
    j = consonant.find (word[i])
    newWord = newWord + word[i]  # the orginal letter is always copied over
    if j > -1:                   # if it's not a vowel add the closest vowel and
                                 # next consonant   
        newWord = newWord + closestVowel [j] + nextConsonant[j]

print newWord
