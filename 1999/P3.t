% CCC 1999
% problem C: Divided Fractals
%
% Print a section of the classic square fractal 

% create the full fractal square (s) using straight forward recursion
% but only print a section of it.

% file handling is used, the number of fractals is given
% n = number of iterations
% b,t,l,r (bottom, top, left and right) coordinates to print



var infile : string := "frac.in"
var outfile : string := "frac.out"
var fi, fo : int
var count : int
var n, t, b, l, r : int
var s : array 1 .. 243, 1 .. 243 of string (1)

% create the fractal square:
% erase stars, given the top left corner and level of square
% by erasing 3^(level-1) stars in centre (width/height is 3^level)
% recursively call the 8 sub squares.
procedure square (r : int, c : int, level : int)
    if level >= 1 then
        var n : int := 3 ** (level - 1)
        for i : r + n .. r + 2 * n - 1
            for j : c + n .. c + 2 * n - 1
                s (i, j) := " "
            end for
        end for
        square (r, c, level - 1)
        square (r, c + n, level - 1)
        square (r, c + 2 * n, level - 1)
        square (r + n, c, level - 1)
        square (r + n, c + 2 * n, level - 1)
        square (r + 2 * n, c, level - 1)
        square (r + 2 * n, c + n, level - 1)
        square (r + 2 * n, c + 2 * n, level - 1)
    end if
end square

open : fi, infile, get
open : fo, outfile, put
get : fi, count
for ii : 1 .. count
    get : fi, n
    get : fi, b
    get : fi, t
    get : fi, l
    get : fi, r
    var k : int := 3 ** n
    for i : 1 .. k
        for j : 1 .. k
            s (i, j) := "*"
        end for
    end for
    square (1, 1, n)
    for decreasing i : t .. b
        for j : l .. r
            put : fo, s (i, j), " " ..
        end for
       put : fo, ""
    end for
    put : fo, ""
end for

close : fi
close : fo





