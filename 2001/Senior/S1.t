% CCC 2001
% Problem J3S1: Keeping Score

% determine the points of a Bridge hand.
% ace = 4, king = 3, queen = 2 and jack = 1
% void = 3, singleton = 2 and doubleton = 1

% Input: cards come in as a single string, eg:"C258JKD69QAHSTJA"
% output is a list of cards, by suit, points per suit and total points


% Calculate the points for the first suit in a given string 
% The points are returned as well as the shortened string
% (The first suit is removed form the string.
procedure Points (var s : string, var p : int)
    var i : int
    i := 1
    p := 0
    loop
        exit when i > length (s) or s (i) = "C" or s (i) = "D" or s (i) =
            "H" or s (i) = "S"
        put s (i), " " ..
        if s (i) = "J" then
            p := p + 1
        elsif s (i) = "Q" then
            p := p + 2
        elsif s (i) = "K" then
            p := p + 3
        elsif s (i) = "A" then
            p := p + 4
        end if
        i := i + 1
    end loop
    if i = 1 then
        p := p + 3
    elsif i = 2 then
        p := p + 2
    elsif i = 3 then
        p := p + 1
    end if
    s := s (i .. *)
end Points


% Output spacing was arrived at by considering the possibility that
% one suit might contain all 13 cards!

var p, tp : int
var s : string

put "Enter cards:"
get s

tp := 0
put ""
put "Cards Dealt                       Points"

% for each suit, strip off the "C", "D", "H", or "S" and send to 
% Points method above.
s := s (2 .. *)
put "Clubs " ..
Points (s, p)
Text.Locate (Text.WhatRow, 37)
put p : 2
tp := tp + p

s := s (2 .. *)
put "Diamonds " ..
Points (s, p)
Text.Locate (Text.WhatRow, 37)
put p : 2
tp := tp + p

s := s (2 .. *)
put "Hearts " ..
Points (s, p)
Text.Locate (Text.WhatRow, 37)
put p : 2
tp := tp + p

s := s (2 .. *)
put "Spades " ..
Points (s, p)
Text.Locate (Text.WhatRow, 37)
put p : 2
tp := tp + p

put "                              Total ", tp : 2


