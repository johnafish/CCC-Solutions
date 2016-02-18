# CCC 2011 Senior 2: Multiple Choice
# written by C. Robart
# March 2011

# straight forward file handling with
# simple list (1D array) comparision

# file input
file = open("s2.5.in", 'r')
N = eval(file.readline())
studentResponses = []
for i in range (0, N):
    studentResponses.append(file.readline())
correctAnswers = []
for i in range (0, N):
    correctAnswers.append(file.readline())

# mark test
score = 0
for i in range (0, N):
    if studentResponses[i] == correctAnswers[i]:
        score = score + 1
print score
file.close()
    
