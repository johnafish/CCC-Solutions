% J5 2010; Knight Hop
%
% Due to Peter Sun and Wonjohn Choi
% of St Francis Xavier SS
%
% straight forward recursive approach
%

var board : array 1 .. 8, 1 .. 8 of int

procedure knightMove (x : int, y : int, steps : int)
    if x > 0 and x <= 8 and y > 0 and y <= 8 and steps < board (x, y) then
        board (x, y) := steps
        knightMove (x - 1, y + 2, steps + 1)
        knightMove (x - 1, y - 2, steps + 1)
        knightMove (x + 1, y + 2, steps + 1)
        knightMove (x + 1, y - 2, steps + 1)
        knightMove (x - 2, y + 1, steps + 1)
        knightMove (x - 2, y - 1, steps + 1)
        knightMove (x + 2, y + 1, steps + 1)
        knightMove (x + 2, y - 1, steps + 1)
    end if
end knightMove


var xStart, yStart : int
var xEnd, yEnd : int

% get input
get xStart, yStart, xEnd, yEnd

% initialize the board (99999 = haven't got there yet)
for i : 1 .. 8
    for j : 1 .. 8
        board (i, j) := 99999
    end for
end for

% move everywhere possible
knightMove (xStart, yStart, 0)

% answer is on the board
put board (xEnd, yEnd)

