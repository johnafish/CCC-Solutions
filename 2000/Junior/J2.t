% CCC 2000
% problem J2: 9966

% given a range, find all "rotable" numbers
% eg 1,8,11,69,88,96 (when turned upside down, read the same)

% reverse the number and if 0,1,8 in same spot, okay
% and 6/9 in same spot

% simply look at first and last numbers and see
% if they are both 0,1,or 8 or are the 6/9 pair
% continue in from both directions.

% use an array to hold the number.

% keyboard and screen I/O

function rotatable (xx : int) : boolean
    var a : array 1 .. 5 of int
    var x, i : int
    var okay : boolean := true
    x := xx
    i := 0
    loop
        exit when x = 0
        i := i + 1
        a (i) := x mod 10
        x := x div 10
    end loop
    for j : 1 .. i
        if (a (j) = 0 and a (i - j + 1) = 0) or
                (a (j) = 1 and a (i - j + 1) = 1) or
                (a (j) = 8 and a (i - j + 1) = 8) or
                (a (j) = 9 and a (i - j + 1) = 6) or
                (a (j) = 6 and a (i - j + 1) = 9) then
        else
            okay := false
        end if
    end for
    result okay
end rotatable

var m, n : int
var count : int

put "Enter the lower bound of the interval:"
get m
put ""
put "Enter the upper bound of the interval:"
get n
put ""
count := 0
put "The number of rotatable numbers is:"
for i : m .. n
    if rotatable (i) then
        count := count + 1
    end if
end for
put count

