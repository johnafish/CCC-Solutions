# CCC 2011 Senior 4: Blood Distribution
# written by C. Robart
# March 2011

# the order in which you match up the types makes little
# difference in the CCc test cases
# However Kevin Luo pointed out that problems can arise with
# certain orders, so two different ones are tried and the max taken
#
# eg:
#    0 0 1 0 1 0 0 0
#    0 0 0 0 0 1 1 0
#    
#    0 0 1 0 1 0 0 0
#    0 0 0 1 0 0 1 0
#    
#    0 1 1 0 0 0 0 0
#    0 0 0 1 0 1 0 0
#    
#    0 1 1 0 0 0 0 0
#    0 0 0 1 0 0 1 0
#    
#    0 1 0 0 1 0 0 0
#    0 0 0 1 0 1 0 0
#    
#    0 1 0 0 1 0 0 0
#    0 0 0 0 0 1 1 0
#
#    All the above have an answer of 2. In single ordering will not do that. 
#
#    0 0 2 0 2 0 0 0
#    0 0 0 1 0 1 2 0
#
#    Here is one with an answer of 4.



#finds the min amount of blood[b] and patients[p]
#returns it and reduces the two lists (arrays)
def units(b,p):
    u = min(blood[b], patients[p])
    blood[b] = blood[b] - u
    patients[p] = patients[p] - u 
    return u


# file input
file = open("s4_1_in.txt", 'r')
line = file.readline()
blood = []
for i in range (0,7):
    space = line.find(" ")
    blood.append( eval(line[0:space]))
    line = line[space+1:]
blood.append(eval(line))    

line = file.readline()
patients = []
for i in range (0,7):
    space = line.find(" ")
    patients.append( eval(line[0:space]))
    line = line[space+1:]
patients.append(eval(line))    

holdpatients = list(patients)
holdblood = list(blood)

# try this ordering
total = 0
# O- (take all O- you can)
total = total + units(0,0)
# O+ (take all O+ you can, then take from 0-)
total = total + units(1,1) + units(0,1)
# A- (take all A- you can, then take from 0-)
total = total + units(2,2) + units(0,2)
# B- (take all B- you can, then take from 0-)
total = total + units(4,4) + units(0,4)

# A+ (take all A+ you can, then take from O+)
total = total + units(3,3) + units(1,3) 
# B+ (take all B+ you can, then take from O+)
total = total + units(5,5) + units(1,5)
# A+ (take all A- you can, then take from O-)
total = total + units(2,3) + units(0,3) 
# B+ (take all B- you can, then take from O-)
total = total + units(4,5) + units(0,5)

# AB- (take all AB- you can, then take from B-, A- or O-)
total = total + units(6,6) + units(4,6) + units(2,6) + units(0,6)
# AB+ (take all AB+ you can, then take from AB-, B+, B-, A+, A-, O+ or O-)
total = total + units(7,7) + units(6,7) + units(5,7) + units(4,7) + units(3,7) + units(2,7) + units(1,7) + units(0,7)

print "first order",total

patients = holdpatients
blood = holdblood

# try this second ordering
total2 = 0
# O- (take all O- you can)
total2 = total2 + units(0,0)
# O+ (take all O+ you can, then take from 0-)
total2 = total2 + units(1,1) + units(0,1)
# A- (take all A- you can, then take from 0-)
total2 = total2 + units(2,2) + units(0,2)
# B- (take all B- you can, then take from 0-)
total2 = total2 + units(4,4) + units(0,4)

# A+ (take all A+ you can, then take from A-)
total2 = total2 + units(3,3) + units(2,3)
# B+ (take all B+ you can, then take from B-)
total2 = total2 + units(5,5) + units(4,5)
# A+ (take all O+ you can, then take from O-)
total2 = total2 + units(1,3) + units(0,3) 
# B+ (take all O+ you can, then take from O-)
total2 = total2 + units(1,5) + units(0,5)

# AB- (take all AB- you can, then take from B-, A- or O-)
total2 = total2 + units(6,6) + units(4,6) + units(2,6) + units(0,6)
# AB+ (take all AB+ you can, then take from AB-, B+, B-, A+, A-, O+ or O-)
total2 = total2 + units(7,7) + units(6,7) + units(5,7) + units(4,7) + units(3,7) + units(2,7) + units(1,7) + units(0,7)

print "second order",total2


print max (total, total2)


