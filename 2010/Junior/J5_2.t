% J5 2010; Knight Hop
%
% 2D array for the board
% 1D array of points to do the BFS
%

class Point
    export var x, var y, setValue
    var x, y : int

    procedure setValue (a, b : int)
	x := a
	y := b
    end setValue
end Point


var board : array 1 .. 8, 1 .. 8 of int

var p : array 1 .. 64 of ^Point
var q : array 1 .. 64 of ^Point
var psize, qsize : int

var x, y : int
var sx, sy : int
var a, b, step : int

% get input
get x, y, sx, sy

%initialize array of points (tracks latest moves)
new p (1)
p (1) -> setValue (x, y)
psize := 1

% initialize the board (99999 = haven't got there yet)
for i : 1 .. 8
    for j : 1 .. 8
	board (i, j) := 99999
    end for
end for
board (x, y) := 0

%move EVERYWHERE possible
step := 1
loop
    exit when psize = 0

    % move to the next spots and remember where you moved to in q
    qsize := 0
    for i : 1 .. psize
	a := p (i) -> x
	b := p (i) -> y

	% try all 8 possible moves
	if a + 1 <= 8 and b + 2 <= 8 and step < board (a + 1, b + 2) then
	    board (a + 1, b + 2) := step
	    qsize := qsize + 1
	    new q (qsize)
	    q (qsize) -> setValue (a + 1, b + 2)
	end if
	if a + 2 <= 8 and b + 1 <= 8 and step < board (a + 2, b + 1) then
	    board (a + 2, b + 1) := step
	    qsize := qsize + 1
	    new q (qsize)
	    q (qsize) -> setValue (a + 2, b + 1)
	end if
	if a + 2 <= 8 and b - 1 >= 1 and step < board (a + 2, b - 1) then
	    board (a + 2, b - 1) := step
	    qsize := qsize + 1
	    new q (qsize)
	    q (qsize) -> setValue (a + 2, b - 1)
	end if
	if a + 1 <= 8 and b - 2 >= 1 and step < board (a + 1, b - 2) then
	    board (a + 1, b - 2) := step
	    qsize := qsize + 1
	    new q (qsize)
	    q (qsize) -> setValue (a + 1, b - 2)
	end if
	if a - 1 >= 1 and b - 2 >= 1 and step < board (a - 1, b - 2) then
	    board (a - 1, b - 2) := step
	    qsize := qsize + 1
	    new q (qsize)
	    q (qsize) -> setValue (a - 1, b - 2)
	end if
	if a - 2 >= 1 and b - 1 >= 1 and step < board (a - 2, b - 1) then
	    board (a - 2, b - 1) := step
	    qsize := qsize + 1
	    new q (qsize)
	    q (qsize) -> setValue (a - 2, b - 1)
	end if
	if a - 2 >= 1 and b + 1 <= 8 and step < board (a - 2, b + 1) then
	    board (a - 2, b + 1) := step
	    qsize := qsize + 1
	    new q (qsize)
	    q (qsize) -> setValue (a - 2, b + 1)
	end if
	if a - 1 >= 1 and b + 2 <= 8 and step < board (a - 1, b + 2) then
	    board (a - 1, b + 2) := step
	    qsize := qsize + 1
	    new q (qsize)
	    q (qsize) -> setValue (a - 1, b + 2)
	end if
    end for

    % get set for the next round of moving
    % (its one more step and set p = q)
    step := step + 1
    psize := qsize
    for i : 1 .. psize
	p (i) := q (i)
    end for
end loop

% answer in on the board
put board (sx, sy)
