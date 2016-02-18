% CCC 2008
% J3: GPS
%
% given the grid
%  ABCDEF
%  GHIJKL
%  MNOPQR
%  STUVWX
%  YZ -.enter
%
% given a phrase, determine the number
% of movements in the grid to type the
% phrase (no diagonals nor wrap around)
%
% start at A
%
% its a question of changing Letters to coordinates
% and then getting the distance between, so to speak.
%

var phrase : string (40)
var letter : char
var x : int
var movements, r, c, newr, newc : int

get phrase : *
movements := 0
r := 1
c := 1
for i : 1 .. length (phrase)
    letter := phrase (i)
    
    % if this div mod business to too complex
    if letter >= 'A' and letter <= 'Z' then
        x := ord (letter) - ord ('A') + 1
        newr := (x - 1) div 6 + 1
        newc := (x - 1) mod 6 + 1
    elsif letter = ' ' then
        newr := 5
        newc := 3
    elsif letter = '-' then
        newr := 5
        newc := 4
    elsif letter = '.' then
        newr := 5
        newc := 5
    end if
    movements := movements + abs (newr - r) + abs (newc - c)
    r := newr
    c := newc
end for

% don't forget to go to the enter at (5, 6)
movements := movements + abs (newr - 5) + abs (newc - 6)

put movements





