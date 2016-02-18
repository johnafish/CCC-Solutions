% CCC 2003
% Problem J1: Trident
%
% Simple "*" printing program. Lots of Loops :-)
% for the height of the tines, spaces between and 
% height of the handle.
%
% keyboard and screen I/O

var t, s, h : int
put "Enter tine length:"
get t
put "enter tine spacing:"
get s
put "Enter handle length:"
get h
for i : 1 .. t
    put "*" ..
    for j : 1 .. s
        put " " ..
    end for
    put "*" ..
    for j : 1 .. s
        put " " ..
    end for
    put "*"
end for
for i : 1 .. 2 * s + 3
    put "*" ..
end for
put ""
for i : 1 .. h
    for j : 1 .. s + 1
        put " " ..
    end for
    put "*"
end for


