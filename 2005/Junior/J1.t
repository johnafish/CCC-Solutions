% CCC 2005 Junior problem 1
% The Cell Sell
%
% comparing two cellphone plans, with various rates depending on time
% straight forward arithmetic and decision structures

var day, night, weekend : int
var planA, planB : real
put "Number of daytime minutes?"
get day
put "Number of evening minutes?"
get night
put "Number of weekend minutes?"
get weekend
if day > 100 then
    planA := (day - 100) * .25 + night * .15 + .20 * weekend
else
    planA := night * .15 + .20 * weekend
end if
if day > 250 then
    planB := (day - 250) * .45 + night * .35 + .25 * weekend
else
    planB := night * .35 + .25 * weekend
end if
put "Plan A costs ", planA : 0 : 2
put "Plan B costs ", planB : 0 : 2

% abs needed because turing stores reals with a potential small error
if abs (planA - planB) < 0.00005 then
    put "Plan A and B are the same price."
elsif planA > planB then
    put "Plan B is the cheapest."
else
    put "Plan A is the cheapest."
end if


