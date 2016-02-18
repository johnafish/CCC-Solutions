% CCC 2000
% problem J1: Calendar

% given a start day and # of days, print the monthly calendar
% Easy: print spaces on first line to get started then using a counter
% drop down a line every 7 numbers. (Each number occupies 4 places except
% first number occupies 3.)

% keyboard and screen I/O

var s, n : int

put "Enter day:"
get s
put ""
put "Enter the number of days in the month:"
get n
put ""
put "Sun Mon Tue Wed Thr Fri Sat"
for i : 1 .. s - 1
    if i = 1 then
        put "   " ..
    else
        put "    " ..
    end if
end for

for i : 1 .. n
    if s = 1 then
        put i : 3 ..
    else
        put i : 4 ..
    end if
    s := s + 1
    if s > 7 then
        put ""
        s := 1
    end if
end for

