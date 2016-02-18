% CCC 2007 J1: Who is in the Middle
%
%  Simple if else condition

var a, b, c : int
get a, b, c
if (b <= a and a <= c) or (b >= a and a >= c) then
    put a
elsif (a <= b and b <= c) or (a >= b and b >= c) then
    put b
else
    put c
end if

