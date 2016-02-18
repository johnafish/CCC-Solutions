% CCC 2006 Junior problem 2
% Roll the Dice
%
% given to dice with m and n sides, (from 1 to m or n)
% calculate the number of combos which add to 10

% a simple loop does the trick

var n, m : int
var c : int

put "Enter m: "
get m
put "Enter n: "
get n

c := 0
for i : 1 .. m
    if 10 - i <= n and 10 - i > 0 then
        c := c + 1
    end if
end for

put ""
if c = 1 then
    put "There is ", c, " way to get the sum 10."
else
    put "There are ", c, " ways to get the sum 10."
end if

