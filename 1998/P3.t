% CCC 1998
% problem C: Mars Rover

% a rover moves away form origin with a series of aheads, right and left turns
% get it back in minimum steps.

% 1 - and an number means ahead
% 2 - right turn
% 3 - left turn

% keep track of x,y and dir (all start at 0)
% to get home, need to consider all 32 possibilities of position and direction

% file handling is used. the number of excursions is given
% a 0 on a line indicates the end of the excursion

% output distance and steps to return to base.

var infile : string := "rover.in"
var outfile : string := "rover.out"
var fi, fo : int
var n, i, d : int
var dir, x, y : int


procedure right
    put : fo, "2"
end right

procedure left
    put : fo, "3"
end left

procedure ahead (w : int)
    put : fo, "1"
    put : fo, abs (w)
end ahead



open : fi, infile, get
open : fo, outfile, put

get : fi, n
for k : 1 .. n
    dir := 0
    x := 0
    y := 0
    loop
        get : fi, i
        exit when i = 0
        if i = 1 then
            get : fi, d
            if dir = 0 then
                x := x + d
            elsif dir = 90 then
                y := y + d
            elsif dir = 180 then
                x := x - d
            else
                y := y - d
            end if
        elsif i = 2 then
            dir := dir - 90
            if dir < 0 then
                dir := 270
            end if
        else
            dir := dir + 90
            if dir = 360 then
                dir := 0
            end if
        end if
    end loop

    put : fo, "Distance is ", abs (x) + abs (y)

    % Brute force, all possibliliites (32 of'em)!
    if y > 0 and x > 0 and dir = 0 then
        right
        ahead (y)
        right
        ahead (x)
    elsif y > 0 and x > 0 and dir = 90 then
        left
        ahead (x)
        left
        ahead (y)
    elsif y > 0 and x > 0 and dir = 180 then
        ahead (x)
        left
        ahead (y)
    elsif y > 0 and x > 0 and dir = 270 then
        ahead (y)
        right
        ahead (x)
    elsif y > 0 and x < 0 and dir = 0 then
        ahead (x)
        right
        ahead (y)
    elsif y > 0 and x < 0 and dir = 90 then
        right
        ahead (x)
        right
        ahead (y)
    elsif y > 0 and x < 0 and dir = 180 then
        left
        ahead (y)
        left
        ahead (x)
    elsif y > 0 and x < 0 and dir = 270 then
        ahead (y)
        left
        ahead (x)
    elsif y < 0 and x < 0 and dir = 0 then
        ahead (x)
        left
        ahead (y)
    elsif y < 0 and x < 0 and dir = 90 then
        ahead (y)
        right
        ahead (x)
    elsif y < 0 and x < 0 and dir = 180 then
        right
        ahead (y)
        right
        ahead (x)
    elsif y < 0 and x < 0 and dir = 270 then
        left
        ahead (x)
        left
        ahead (y)
    elsif y < 0 and x > 0 and dir = 0 then
        left
        ahead (y)
        left
        ahead (x)
    elsif y < 0 and x > 0 and dir = 90 then
        ahead (y)
        left
        ahead (x)
    elsif y < 0 and x > 0 and dir = 180 then
        ahead (x)
        right
        ahead (y)
    elsif y < 0 and x > 0 and dir = 270 then
        right
        ahead (x)
        right
        ahead (y)
    elsif y = 0 and x > 0 and dir = 0 then
        right
        right
        ahead (x)
    elsif y = 0 and x > 0 and dir = 90 then
        left
        ahead (x)
    elsif y = 0 and x > 0 and dir = 180 then
        ahead (x)
    elsif y = 0 and x > 0 and dir = 270 then
        right
        ahead (x)
    elsif y > 0 and x = 0 and dir = 0 then
        right
        ahead (y)
    elsif y > 0 and x = 0 and dir = 90 then
        left
        left
        ahead (y)
    elsif y > 0 and x = 0 and dir = 180 then
        left
        ahead (y)
    elsif y > 0 and x = 0 and dir = 270 then
        ahead (y)
    elsif y = 0 and x < 0 and dir = 0 then
        ahead (x)
    elsif y = 0 and x < 0 and dir = 90 then
        right
        ahead (x)
    elsif y = 0 and x < 0 and dir = 180 then
        left
        left
        ahead (x)
    elsif y = 0 and x < 0 and dir = 270 then
        left
        ahead (x)
    elsif y < 0 and x = 0 and dir = 0 then
        left
        ahead (y)
    elsif y < 0 and x = 0 and dir = 90 then
        ahead (y)
    elsif y < 0 and x = 0 and dir = 180 then
        right
        ahead (y)
    elsif y > 0 and x = 0 and dir = 270 then
        left
        left
        ahead (y)
    end if
    put : fo, ""
end for

close : fi
close : fo

