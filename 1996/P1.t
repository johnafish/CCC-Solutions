% CCC 1996
% problem 1: Deficient, Perfect and Abundant Numbers
%
% a perfect number = the sum of its proper divisors (1 thru < n)
% a Deficent number < the sum of its proper divisors (1 thru < n)
% an Abundant number > the sum of its proper divisors (1 thru < n)

% file handling is used, the number of numbers is given
% the numbers will be between 1 and 32500.

function sumFactors (x : int) : int
    var sum : int
    sum := 0
    for f : 1 .. x - 1
        if x mod f = 0 then
            sum := sum + f
        end if
    end for
    result sum
end sumFactors

var infile : string := "dpa.in"
var outfile : string := "dpa.out"
var fi, fo : int
var n : int
var x : int
var sum : int

open : fi, infile, get
open : fo, outfile, put

get : fi, n
for i : 1 .. n
    get : fi, x
    sum := sumFactors (x)
    if sum < x then
        put x, " is a deficient number."
        put : fo, x, " is a deficient number."
    elsif x = sum then
        put x, " is a perfect number."
        put : fo, x, " is a perfect number."
    else
        put x, " is an abundant number."
        put : fo, x, " is an abundant number."
    end if
end for

close : fi
close : fo





