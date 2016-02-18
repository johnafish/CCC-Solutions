#2003 S4 Substrings

# this algorithm is due to Calvin Liu of Glenforest Secondary School
#
# Here is Calvin's explaination:
#
# 1. A suffix array is a sorted array of all suffixes of a string.
#       For example, the suffix array of string "aaaa" is: ["a","aa","aaa","aaaa"].
# 2. It is sorted lexicographically. 
#       For example,["abac","bac","ac","c"] should be ["abac", "ac", "bac", "c"]. 
# 3. Every substring for a string is a prefix of one of the suffixes. 
#	For example, for string "abcd", we can have:
#	SuffixArray = ["abcd","bcd","cd","d"]
#	
#	Suffix    Prefixes            Prefix Count
#	abcd      a,ab,abc,abcd       4
#	bcd       b,bc,bcd            3
#	cd        c,cd                2
#	d         d                   1
#
#	Total number of substrings=4+3+2+1=10, and for that specific question,
#       we have to include the empty string so we get 11 substrings in total.
#	Notice that the number of prefixes a suffix has is just its length.
#
# Now, the last problem is what if the string is "aaaa"?
# We have to eliminate all the duplicated substrings.
# We build the suffix array again like this:
#
# SuffixArray = ["a","aa","aaa","aaaa"]
#	
# Suffix  Prefixes       Prefix Count  LCP  Unique substrings            Unique Count
#                                           compared to the previous one  
# a       a              1             -    a				 1
# aa      a,aa           2             1    aa                           1
# aaa     a,aa,aaa       3             2    aaa			         1
# aaaa    a,aa,aaa,aaaa  4             3    aaaa   		         1
#
# Thus, the total number of different prefixes is 1+(2-1)+(3-2)+(4-3) = 4
# and plus the empty string we get 5.
#
#
# Another example for string "abab":
#
# SuffixArray = ["ab","abab","b","bab"]
#
# Suffix  Prefixes       Prefix Count  LCP  Unique substrings   Unique Count
# ab      a,ab           2             -    a,ab		2
# abab    a,ab,aba,abab  4             2    aba,abab            2
# b       b              1             0    b			1
# bab     b,ba,bab       3             1    ba,bab		2
#
# The total number of different prefixes is 2+(4-2)+(1-0)+(3-1) = 7
# and plus the empty string we get 8.
#
# All three cases are included:
# Worst Case    "aaaa"
# Average Case  "abab"
# Best Case     "abcd"
#


#Find the Largest Common Prefix
def LCP(s, t):
    n = min(len(s), len(t))
    for i in range(n):
        if s[i:i+1] != t[i:i+1]:
            return i
    return n

file = open("substr5.in.txt", 'r')

for n in range(eval(file.readline())):
    string = file.readline().strip()
    
    #Build the suffix array
    suffix = []
    for i in range(len(string)):
        suffix.append(string[i:])
    suffix.sort()

    #The number of substrings a string contains is the length of the suffix minus the length of the longest prefix it has in common with the previous suffix. 
    count = len(suffix[0]) + 1
    for i in range(1, len(string)):
        lcp = LCP(suffix[i], suffix[i - 1])
        count = count + len(suffix[i]) - lcp
        
    print count
