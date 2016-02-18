% CCC 2003
% Problem J3/S1: Snakes and Ladders
%
% simple dice roll input, move the piece. watch for 3 snakes and 3 ladders
% also watch for going over 100. Declare if win or quit.
%
% keyboard and screen I/O

var place, x : int
place := 1
loop
    put "Enter sum of dice:"
    get x
    if x not= 0 then
        if place + x <= 100 then
            place := place + x
        end if
        if place = 9 then
            place := 34
        elsif place = 40 then
            place := 64
        elsif place = 67 then
            place := 86
        elsif place = 99 then
            place := 77
        elsif place = 90 then
            place := 48
        elsif place = 54 then
            place := 19
        end if
        put "You are now on square ", place
    end if
    exit when x = 0 or place = 100
end loop
if place = 100 then
    put "You Win!"
else
    put "You Quit!"
end if

