% CCC 2007 J3: Deal or No Deal Calculator
%
% simple 1D array processing
%

var a : array 1 .. 10 of int
var n, x, sum, offer : int
var avg : real

a (1) := 100
a (2) := 500
a (3) := 1000
a (4) := 5000
a (5) := 10000
a (6) := 25000
a (7) := 50000
a (8) := 100000
a (9) := 500000
a (10) := 1000000

get n
for i : 1 .. n
    get x
    a (x) := 0
end for
sum := 0
for i : 1 .. 10
    sum := sum + a (i)
end for
avg := sum / (10 - n)
get offer
if offer > avg then
    put "deal"
else
    put "no deal"
end if
