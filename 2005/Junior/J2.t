% CCC 2005 Junior problem 2
% RSA numbers
%
% simple arithmetic pgm with structures

function RSA (n : int) : boolean
    var f : int := 2
    var count : int := 1
    loop
        exit when f > n or count > 4
        if n mod f = 0 then
            count := count + 1
        end if
        f := f + 1
    end loop
    result count = 4
end RSA

var x, y, c : int
put "Enter lower limit of range"
get x
put "Enter upper limit of range"
get y
c := 0
for i : x .. y
    if RSA (i) then
        c := c + 1
    end if
end for
put "The number of RSA numbers between ", x, " and ", y, " is ", c

