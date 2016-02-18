% CCC 2007
% J5: Keep on Truckin'
%
% This is a recursive approach created by
% Wen-Hao Lue of Bayview Glen Private School
%
% Recursively find a route to 7000. When you find one
% add one to a counter, otherwise keep going.
%
% In more detail:
%    if your at 7000
%         you made it, add 1 to a counter
%    else you're still on the road
%         recursively call this for EVERY motel 
%             that you can get to from here
%

var motel : array 1 .. 50 of int
var ways : int := 0
var minn, maax, size, n : int

function between (a, b, c : int) : boolean
    result a <= b and b <= c
end between

% this recursively tries every route to the end (7000)
procedure findPath (distance : int)
    if distance = 7000 then
        % made it to the end!
        % One more way to get there
        ways := ways + 1
    
    else
        % recursively try all motels that you can get to
        for i : 1 .. size
            if between (minn, motel (i) - distance, maax) then
                findPath (motel (i))
            end if
        end for
    end if
end findPath

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

% find all the ways from 0
findPath (0)

put ways





