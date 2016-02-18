% CCC 1996
% problem e Maximum Distance

% calculate the max distance between two descending sequences
% distance(xi,yj) = j-i if j>=i and yj >= xi (or 0 otherwise)
% the distance between the sequences is the max distance for all i and j

% file i/o used.
% the first number is the number of sequence pairs.
% each pair is preceeded by the size.


var infile : string := "max.in"
var outfile : string := "max.out"
var fi, fo : int
var n, d, j : int
var x, y : array 1 .. 1000 of int
var size : int

open : fi, infile, get
open : fo, outfile, put

get : fi, n
for k : 1 .. n

    % get the sequences
    get : fi, size
    for i : 1 .. size
        get : fi, x (i)
    end for
    for i : 1 .. size
        get : fi, y (i)
    end for

    % for each number in x, work backward thru y to find the last position of
    % y(j) >= x(i). If j > i and j-i > previous max, save it in max (d)
    d := 0
    for i : 1 .. size
        j := size
        loop
            exit when j <= i or y (j) >= x (i)
            j := j - 1
        end loop
        if j > i and j - i > d then
            d := j - i
        end if
    end for
    put : fo, "The maximum distance is ", d
    put : fo, ""
end for
close : fi
close : fo

