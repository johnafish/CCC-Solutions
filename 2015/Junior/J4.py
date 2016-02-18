# CCC 2015 Junior 4: Wait Time
#
# Consider this a 2D array problem, or an array of records
#
# For this I use and array of elements called "database"
# Each element is itself an array of 3 numbers: 
#     the friend #, time of message, and the total wait time for this friend
#
# I need it organized like this so it can be sorted by friend number at the end.
#
# Also the time of message will be set to -1 when answered, 
# so at the end I can detect if there are unanswered messages.
#
# eg [12,6,2] means friend 12 has made a call at time 6 (its not replied to so far)
#             and he/she has a wait time of 2 from previous message(s)

file = open("j4.3.in", "r")
m = int(file.readline())

database = []
time = -1
for i in range(m):
    line = file.readline().split()
    letter = line[0]
    friend = int(line[1])

    if letter == "W":
        time = time + friend - 1   # in this case friend is really a time
                                   # the -1 is there because +1 will be added next time
    else:
        time = time + 1

    if letter != "W":
        # search database for this friend
        found = False
        k = 0
        while not found and k < len(database):
            if friend == database[k][0]:
                found = True
            else:
                k = k + 1

        if letter == "R":       
            #if found, put inthe time of the message, otherwise add a new element to the database
            if found:
                database[k][1] = time
            else:
                database.append ([friend, time, 0])
        elif letter == "S":
            # it will be found, so no check is done
            database[k][2] = database[k][2] + (time - database[k][1])
            database[k][1] = -1

# Before printing the results the database must be sorted by friend number
# A simple bubble sort is used
for i in range(len(database)-1):
    for j in range(i+1, len(database)):
        if database[i][0] > database[j][0]:
            temp = database[i]
            database[i] = database[j]
            database[j] = temp

# Print results
for i in range(len(database)):
    if database[i][1] == -1:
        print database[i][0], database[i][2]
    else:
        print database[i][0], -1




            
        
