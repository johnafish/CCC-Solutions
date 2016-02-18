% CCC 1999
% problem A: CardGame
%
% read a deck of 52 cards (words)
% high cards = jack and above.

% keep score (alternating between players)

% ace + none of next 4 are high cards = 4pts
% king + none of next 3 are high cards = 3pts
% queen + none of next 2 are high cards = 2pts
% jack + none of next 1 are high cards = 1pt

% file handling is used, the 52 cards given

var infile : string := "card.in"
var outfile : string := "card.out"
var fi, fo : int
var card : array 1 .. 52 of string
var a, b, v : int

% checking if next x cards, starting at i, contain a high card. 
% at end of deck return false because no points are scored.
function noHigh (i : int, x : int) : boolean
    if i + x > 52 then
        result false
    end if
    for j : i + 1 .. i + x
        if card (j) = "ace"
                or card (j) = "king"
                or card (j) = "queen"
                or card (j) = "jack" then
            result false
        end if
    end for
    result true
end noHigh

open : fi, infile, get
open : fo, outfile, put
a := 0
b := 0
for i : 1 .. 52
    get : fi, card (i)
end for

for i : 1 .. 52
    if card (i) = "ace" and noHigh (i, 4) then
        v := 4
    elsif card (i) = "king" and noHigh (i, 3) then
        v := 3
    elsif card (i) = "queen" and noHigh (i, 2) then
        v := 2
    elsif card (i) = "jack" and noHigh (i, 1) then
        v := 1
    else
        v := 0
    end if
    if v > 0 then
        var p : string
        if i mod 2 = 1 then
            p := "A"
            a := a + v
        else
            p := "B"
            b := b + v
        end if
        put : fo, "Player ", p, " scores ", v, " point(s)."
    end if
end for

put : fo, "Player A: ", a, " point(s)."
put : fo, "Player B: ", b, " point(s)."

close : fi
close : fo





