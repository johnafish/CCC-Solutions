% CCC 2001
% Problem J1: Dressing Up

% Create a bowtie star pattern given the height (and odd > 5) of the tie.
% use for loops and variables to track the number of stars and spaces
% to print on each line. Pattern reverses half way.

% Keybd/Screen I/O

var h : int
var spaces : int
var stars : int
put "Enter height:"
get h
spaces := 2 * h - 2
stars := 1
for i : 1 .. h
    for j : 1 .. stars
        put "*" ..
    end for
    for j : 1 .. spaces
        put " " ..
    end for
    for j : 1 .. stars
        put "*" ..
    end for
    if i <= h div 2 then
        spaces := spaces - 4
        stars := stars + 2
    else
        spaces := spaces + 4
        stars := stars - 2
    end if
    put ""
end for

