% CCC 2009
% J2: Old Fishin' Hole
%
% a series of nested loops
%

var total, troutPoints, pikePoints, pickerelPoints : int
var count : int

get troutPoints
get pikePoints
get pickerelPoints
get total

count := 0

for t : 0 .. total div troutPoints
    for p : 0 .. total div pikePoints
        for q : 0 .. total div pickerelPoints
            if (t > 0 or p > 0 or q > 0) and (t * troutPoints + p *
                    pikePoints + q * pickerelPoints <= total) then
                count := count + 1
                put t, " Brown Trout, ", p, " Northern Pike, ", q, 
                    " Yellow Pickerel"
            end if
        end for
    end for
end for
put "Number of ways to catch fish: ", count

