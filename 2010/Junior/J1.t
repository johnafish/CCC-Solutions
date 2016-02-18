% J1: 2010: What is n, Daddy?
%
% big if (or a formula if you prefer)
%

var n : int

get n

% it's just a big if.
if n = 1 then
    put 1
elsif n = 2 then
    put 2
elsif n = 3 then
    put 2
elsif n = 4 then
    put 3
elsif n = 5 then
    put 3
elsif n = 6 then
    put 3
elsif n = 7 then
    put 2
elsif n = 8 then
    put 2
elsif n = 9 then
    put 1
elsif n = 10 then
    put 1
end if

% alternative to the big if: calculate answer
put 3 - abs (5 - n) div 2
