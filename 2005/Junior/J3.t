% CCC 2005 Junior problem 3
% Returning Home
%
% simple array and string processing

var out : array 1 .. 10 of string
var n : int
var s : string
var hold : string
n := 0
hold := "into your HOME."
loop
    get s
    n := n + 1
    if s = "L" then
        out (n) := "Turn RIGHT " + hold
    else
        out (n) := "Turn LEFT " + hold
    end if
    get s
    hold := "onto " + s + " Street."
    exit when s = "SCHOOL"
end loop
for decreasing i : n .. 1
    put out (i)
end for
