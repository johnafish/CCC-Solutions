% 2004 J5: Fractals
%
% Long ugly array and geometry calculations
% using 1D array storage
% 
% ox,oy are arrays of the old endpoints of the lines making up the fractal
% using these lines, the nx,ny arrays of end points are created.
% (brute force: all 4 directions are considered.)
% then the old arrays are replaced by the new ones
% repeat for all the levels
% 
% finally all the points (xcoor,y) where y = 1,2,3,... are checked to see if 
% they are on any of the lines. If they are, the 

var ox : array 1 .. 7000 of int
var oy : array 1 .. 7000 of int
var nx : array 1 .. 7000 of int
var ny : array 1 .. 7000 of int
var ans : array 1 .. 100 of int
var level, xcoor, width, w, size, k, n : int
var done : boolean

get level, width, xcoor
ox (1) := 0
oy (1) := 1
ox (2) := width
oy (2) := 1
size := 2
for i : 1 .. level
    k := 0
    for j : 1 .. size - 1 % for each line
        k := k + 1
        nx (k) := ox (j)
        ny (k) := oy (j)
        if oy (j) = oy (j + 1) and ox (j + 1) > ox (j) then % right
            w := (ox (j + 1) - ox (j)) div 3
            k := k + 1
            nx (k) := ox (j) + w
            ny (k) := oy (j) + 0
            k := k + 1
            nx (k) := ox (j) + w
            ny (k) := oy (j) + w
            k := k + 1
            nx (k) := ox (j) + 2 * w
            ny (k) := oy (j) + w
            k := k + 1
            nx (k) := ox (j) + 2 * w
            ny (k) := oy (j) + 0
            k := k + 1
            nx (k) := ox (j + 1)
            ny (k) := oy (j + 1)
        elsif oy (j) = oy (j + 1) and ox (j + 1) < ox (j) then % left
            w := (ox (j) - ox (j + 1)) div 3
            k := k + 1
            nx (k) := ox (j) - w
            ny (k) := oy (j) + 0
            k := k + 1
            nx (k) := ox (j) - w
            ny (k) := oy (j) - w
            k := k + 1
            nx (k) := ox (j) - 2 * w
            ny (k) := oy (j) - w
            k := k + 1
            nx (k) := ox (j) - 2 * w
            ny (k) := oy (j) + 0
            k := k + 1
            nx (k) := ox (j + 1)
            ny (k) := oy (j + 1)
        elsif ox (j) = ox (j + 1) and oy (j + 1) < oy (j) then % down
            w := (oy (j) - oy (j + 1)) div 3
            k := k + 1
            nx (k) := ox (j) + 0
            ny (k) := oy (j) - w
            k := k + 1
            nx (k) := ox (j) + w
            ny (k) := oy (j) - w
            k := k + 1
            nx (k) := ox (j) + w
            ny (k) := oy (j) - 2 * w
            k := k + 1
            nx (k) := ox (j) + 0
            ny (k) := oy (j) - 2 * w
            k := k + 1
            nx (k) := ox (j + 1)
            ny (k) := oy (j + 1)
        elsif ox (j) = ox (j + 1) and oy (j + 1) > oy (j) then % up
            w := (oy (j + 1) - oy (j)) div 3
            k := k + 1
            nx (k) := ox (j) + 0
            ny (k) := oy (j) + w
            k := k + 1
            nx (k) := ox (j) - w
            ny (k) := oy (j) + w
            k := k + 1
            nx (k) := ox (j) - w
            ny (k) := oy (j) + 2 * w
            k := k + 1
            nx (k) := ox (j) + 0
            ny (k) := oy (j) + 2 * w
            k := k + 1
            nx (k) := ox (j + 1)
            ny (k) := oy (j + 1)
        end if
    end for
    size := k
    for m : 1 .. size
        ox (m) := nx (m)
        oy (m) := ny (m)
    end for
end for

% graph, just for fun :-)
for m : 1 .. size - 1
    drawline (ox (m) * 5, oy (m) * 5, ox (m + 1) * 5, oy (m + 1) * 5,
        black)
end for


% check if the points (xcoor,1), (xcoor,2), ... are on any lines.
for i : 1 .. 81
    k := 1
    done := false
    loop
        exit when k = size or done
        if ox (k) = ox (k + 1) and oy (k) < oy (k + 1)
                and xcoor = ox (k) and (i >= oy (k) and i <= oy (k + 1)) then
            put i, " " ..
            done := true
        elsif ox (k) = ox (k + 1) and oy (k) > oy (k + 1)
                and xcoor = ox (k) and (i <= oy (k) and i >= oy (k + 1)) then
            put i, " " ..
            done := true
        elsif oy (k) = oy (k + 1) and ox (k) < ox (k + 1)
                and xcoor >= ox (k) and xcoor <= ox (k + 1) and i = oy (k)
                then
            put i, " " ..
            done := true
        elsif oy (k) = oy (k + 1) and ox (k) > ox (k + 1)
                and xcoor <= ox (k) and xcoor >= ox (k + 1) and i = oy (k)
                then
            put i, " " ..
            done := true
        end if
        k := k + 1
    end loop
end for

