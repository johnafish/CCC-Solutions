# CCC 2011 Junior 1: Which Alien?
# written by C. Robart
# March 2011

# simple ifs

antenna = input("How many antennas?\n")
eyes = input("How many eyes?\n")

if antenna >= 3 and eyes <= 4:
    print "TroyMartian"
if antenna <= 6 and eyes >= 2:
    print "VladSaturnian"
if antenna <= 2 and eyes <= 3:
    print "GraemeMercuian"
