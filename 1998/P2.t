% CCC 1998
% problem B: cross-Number Puzzle

% find all the perfect numbers from 1000 to 9999 (perfect.out)
% find all numbers from 100 to 999 which are = sum of the cubes
% of their digits. (cube.out)

var perffile : string := "perfect.out"
var cubefile : string := "cube.out"
var fo : int
var n, s, f, d : int


% perfect number section
open : fo, perffile, put
for x : 1000 .. 9999
    s := 0
    f := 1
    loop
        exit when f > x div 2
        if x mod f = 0 then
            s := s + f
        end if
        f := f + 1
    end loop
    if s = x then
        put : fo, x
        put x
    end if
end for
close : fo


% sum of cubes section
open : fo, cubefile, put
for x : 100 .. 999
    s := 0
    n := x
    loop
        exit when n = 0
        d := n mod 10
        n := n div 10
        s := s + d * d * d
    end loop
    if s = x then
        put : fo, x
        put x
    end if
end for
close : fo



