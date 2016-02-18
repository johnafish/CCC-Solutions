% CCC 2001
% Problem J4S2: Spirals

% Given a start and end values, print a number spiral.
% Start in centre and then go down, right, up, and left, repeating to end

% down and right both print the same number of numbers. Then increase by 1
% to print the up and laft numbers. Increase up 1 to start down and right
% again.

% starting location was arbitrarily choosen out in the centre of screen,
% big enough to handle 100 numbers. (5 columns to the left at 3 places each)

% keybd/screen I/O

var start, stop : int
var i, j : int % counters
var r, c : int % current position
var k : int % the number of numbers to print in a direction

put "Start value:"
get start
put "End value:"
get stop


r := 12
c := 20
k := 0
i := start
loop
    exit when i > stop
    k := k + 1

    %down
    j := 1
    loop
        exit when j > k or i > stop
        Text.Locate (r, c)
        put i : 3 ..
        r := r + 1
        j := j + 1
        i := i + 1
    end loop

    %right
    j := 1
    loop
        exit when j > k or i > stop
        Text.Locate (r, c)
        put i : 3 ..
        c := c + 3
        j := j + 1
        i := i + 1
    end loop

    k := k + 1

    %up
    j := 1
    loop
        exit when j > k or i > stop
        Text.Locate (r, c)
        put i : 3 ..
        r := r - 1
        j := j + 1
        i := i + 1
    end loop

    %left
    j := 1
    loop
        exit when j > k or i > stop
        Text.Locate (r, c)
        put i : 3 ..
        c := c - 3
        j := j + 1
        i := i + 1
    end loop
end loop

