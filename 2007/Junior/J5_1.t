% CCC 2007
% J5: Keep on Truckin'
%
%  Arrays and dynamic programming :-)
%
% Step 1: sort the motels
% Step 2: find the first motels you can get to on day one.
% step 3: (the hard step)
%         The idea is to find all the ways to
%         EVERY motel, not just the last.
%         For each motel:
%             find out which motels to can get to from there
%                     (subtract the distances)
%             The key thing is:
%                  suppose you can get to motel D in 2 ways.
%                  suppose you can get to motel K in 3 ways,
%                      (without going thru D first, maybe thru A, B and C).
%                  then if you can get from D to K,
%                    the numbers of ways to K is 2 + 3 = 5 ways!
%

var motel : array 1 .. 50 of int
var ways : array 1 .. 50 of int
var minn, maax, size, n : int

% insertion sort to get the motels in order after input
procedure sortMotels
    var j, hold : int
    for i : 2 .. size
        hold := motel (i)
        j := i - 1
        loop
            exit when j < 1 or motel (j) < hold
            motel (j + 1) := motel (j)
            j := j - 1
        end loop
        motel (j + 1) := hold
    end for
end sortMotels

function between (a, b, c : int) : boolean
    result a <= b and b <= c
end between


size := 13
motel (1) := 990
motel (2) := 1010
motel (3) := 1970
motel (4) := 2030
motel (5) := 2940
motel (6) := 3060
motel (7) := 3930
motel (8) := 4060
motel (9) := 4970
motel (10) := 5030
motel (11) := 5990
motel (12) := 6010
motel (13) := 7000

% get input
get minn
get maax
get n
for i : 1 .. n
    get motel (i + size)
end for
size := size + n
sortMotels

% load the ways for the first motels
for i : 1 .. size
    if between (minn, motel (i), maax) then
        ways (i) := 1
    else
        ways (i) := 0
    end if
end for

% work thruough all the motels and all the ways
% answering "can you get to the next motel?"
for i : 1 .. size - 1

    % can you get to i?
    if ways (i) > 0 then

        for j : i + 1 .. size

            % can you get from i to j?
            if between (minn, motel (j) - motel (i), maax) then
                ways (j) := ways (j) + ways (i)
            end if
        end for
    end if
end for

put ways (size)





