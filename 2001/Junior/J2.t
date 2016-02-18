% CCC 2001
% Problem J2: Mod Inverse

% Calculate the mod inverse of a number
% given x and m such that 0 < x < m then 
% the mod inverse of x is: n, such that 0 < n < m and (x*n) mod m = 1

% Keybd/Screen I/O

var x, m, n : int
put "Enter x:"
get x
put "Enter m:"
get m
n := 1
loop
    exit when n >= m or x * n mod m = 1
    n := n + 1
end loop
put ""
if n >= m then
    put "No such integer exists."
else
    put n
end if

