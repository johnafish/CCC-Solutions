% 2004 J3: Similes
%
% nested loops print all combinations of two arrays
%

var m, n : int
var adj : array 1 .. 5 of string
var noun : array 1 .. 5 of string

get n
get m
for i : 1 .. n
    get adj (i)
end for
for i : 1 .. m
    get noun (i)
end for
for i : 1 .. n
    for j : 1 .. m
        put adj (i), " as ", noun (j)
    end for
end for
