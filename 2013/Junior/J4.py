# CCC 2013 Junior 4: Time on Task

# find the max number of given numbers which sum <= a given total

# read the numbers, sort them and add them up, stopping when the sum > total
# or we hit the end of the numbers
# so if the sum > total the answer is the count - 1
# otherwise its just the count


t = input()
c = input()
x = []
for i in range(c):
    x.append(input())

x.sort()

sum = 0
count = 0
while count < len(x) and sum <= t:
    sum = sum + x[count]
    count = count + 1
if sum > t:
    print count - 1
else:
    print count
