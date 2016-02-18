% CCC 2000
% problem J4: Babbling Brooks

% calculate the flow in 1 to n rivers

% file handling is used
% n is given, then the flow in each of the n rivers
% 99 - split, the river number and % of flow to left
% 88 - join, the river number that is rejoined with one to its right
% 77 - stop processing

% print the flow (rounded) in rivers 1 to n
% relatively simple array processing is used. Trick is to insert and delete
% elements with shifting.

% use reals and round answers.


var fi, fo : int
var f : string
var b : array 1 .. 100 of real
var x : int
var j : int
var p, hold : real
var n : int

put "Input file: " ..
get f
open : fi, f, get
put "Output file: " ..
get f
open : fo, f, put
get : fi, n
for i : 1 .. n
    get : fi, b (i)
end for
loop
    get : fi, x
    exit when x = 77
    if x = 99 then
        get : fi, j
        get : fi, p
        n := n + 1
        for decreasing k : n .. j + 2
            b (k) := b (k - 1)
        end for
        hold := b (j)
        b (j) := hold * (p / 100)
        b (j + 1) := hold - b (j)
    elsif x = 88 then
        get : fi, j
        b (j) := b (j) + b (j + 1)
        for k : j + 2 .. n
            b (k - 1) := b (k)
        end for
        n := n - 1
    end if
end loop
for k : 1 .. n
    put : fo, round (b (k)), " " ..
end for
put : fo, ""
close : fi
close : fo
put "Done!"

