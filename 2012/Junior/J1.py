# J1 2012: Speed fines are not fine!
#
# simple decision and watch the formatting!
#

speedLimit = input("Enter the speed limit:  ")
recordedSpeed  = input("Enter the recorded speed or the car:  ")

if recordedSpeed <= speedLimit:
    print "Congratulations, you are within the speed limit!"
elif recordedSpeed <= speedLimit + 20:
    print "You are speeding and your fine is $100."
elif recordedSpeed <= speedLimit + 30:
    print "You are speeding and your fine is $270."
else:
    print "You are speeding and your fine is $500."
    
