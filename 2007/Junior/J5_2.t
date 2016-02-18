% CCC 2007
% J5: Keep on Truckin'
%
%  Arrays and dynamic programming
%  This approach is due to Konstantin Lopyrev
%
%  This is a more straight forward dp approach 
%  than my version. Simply stated the ways to a motel i
%  = the sum of all ways to previous motels in the range
%  i-max to i-min. 
%
%  The use of a boolean array eliminates the need for
%  sorting. 

var motel : array 0 .. 7000 of boolean
var ways : array 0 .. 7000 of int
var minn, maax, n, d, a : int

for i : 0 .. 7000
    motel (i) := false
end for
motel (0) := true
motel (990) := true
motel (1010) := true
motel (1970) := true
motel (2030) := true
motel (2940) := true
motel (3060) := true
motel (3930) := true
motel (4060) := true
motel (4970) := true
motel (5030) := true
motel (5990) := true
motel (6010) := true
motel (7000) := true

% get input
get minn
get maax
get n
for i : 1 .. n
    get d
    motel (d) := true
end for

% you can get to zero 1 way
ways (0) := 1

for i : 1 .. 7000

    % for each motel
    if motel (i) then
        
        % the ways to that motel = the sum of the ways
        % from the previous motels in the range
        % i-max to i-min (check that i-max is not less than 0)
        ways (i) := 0
        a := i - maax
        if a < 0 then
            a := 0
        end if
        for j : a .. i - minn
            if motel (j) then
                ways (i) := ways (i) + ways (j)
            end if
        end for
    end if
end for

put ways (7000)





