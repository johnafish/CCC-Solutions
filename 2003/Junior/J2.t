% CCC 2003
% Problem J2: Picture Perfect
%
% A rectangle of a given area has
% has a minimum perimeter if arranged as a square (or closest to it)
% so find the square root of the area (number of pictures)
% and then find the largest integer <= that square root 
% That solves the problem
%
% keyboard and screen I/O

var a, l, w : int
loop
    put "Enter number of pictures:"
    get a
    exit when a = 0
    l := round (sqrt (a))
    loop
        exit when a mod l = 0
        l := l - 1
    end loop
    w := a div l
    put "Minimum perimeter is ", 2 * l + 2 * w, " with dimensions ", w, 
        " X ", l
    put ""
end loop

