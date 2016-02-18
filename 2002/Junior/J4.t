% CCC 2002
% Problem S2J4: Fraction Action

% reduce fraction to lowest terms, 
% print as mixed number
% no negatives used

% keybd and screen i/o

% recursive version of Euclid's GCD algorithm
function gcd (m, n : int) : int
    if n = 0 then
        result m
    else
        result gcd (n, m mod n)
    end if
end gcd

var num, den, hold : int

put "Numerator"
get num
put "Denominator"
get den
hold := num
num := num div gcd (num, den)
den := den div gcd (hold, den)
if num > den then
    put num div den, " " ..
end if
if num mod den > 0 then
    put num mod den, "/", den
end if

