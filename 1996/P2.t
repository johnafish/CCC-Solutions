% CCC 1996
% problem 2 Divisibility by 11

% take the last digit off the number and subtract it from the shortened
% number (repeatedly) until the number is 2 digits.
% If n is divisible by 11 the original was divisible by 11.

% file i/o used.
% the first number is the number of numbers.
% numbers may be 50 digits long, no leading zeros.

var infile : string := "div.in"
var outfile : string := "div.out"
var fi, fo : int
var n : int
var line : string
var digit : int
var x : array 1 .. 50 of int
var xn : int

open : fi, infile, get
open : fo, outfile, put

get : fi, n
for i : 1 .. n

    % read the line and convert to integer array
    get : fi, line
    xn := length (line)
    for k : 1 .. xn
        x (k) := strint (line (k))
    end for

    %while the array length is greater than 2
    loop

        % print the array
        for k : 1 .. xn
            put : fo, x (k) ..
            put x (k) ..
        end for
        put : fo, ""
        put ""
        exit when xn <= 2

        % do the subtraction: go left from end, borrowing if necessary
        digit := x (xn)
        xn := xn - 1
        for decreasing j : xn .. 1
            if digit > x (j) then
                x (j) := x (j) + 10
                x (j - 1) := x (j - 1) - 1
            end if
            x (j) := x (j) - digit
            digit := 0
        end for

        % check / remove a leading zero
        if x (1) = 0 then
            xn := xn - 1
            for k : 1 .. xn
                x (k) := x (k + 1)
            end for
        end if
    end loop
    if xn < 2 then
        put : fo, "The number ", line, " is not divisible by 11."
        put "The number ", line, " is not divisible by 11."
    elsif x (1) = x (2) then
        put : fo, "The number ", line, " is divisible by 11."
        put "The number ", line, " is divisible by 11."
    else
        put : fo, "The number ", line, " is not divisible by 11."
        put "The number ", line, " is not divisible by 11."
    end if
    put : fo, ""
    put ""
end for

close : fi
close : fo


