% J4: 2010: Global Warming
%
% while not totally obvious from the given examples
% you need to find a repeated parttern, STARTING FROM THE FIRST
% position in the difference array.
%
% To find that pattern length, try pattern lengths of 1, 2, 3, ..
% if its a true pattern, then
% diff(a) and diff(a+pattern) (for all a >=1) should be equal.



var n, a, b : int
var diff : array 1 .. 19 of int
var pattern : int

loop
    get n
    exit when n = 0

    % handle the case of size 1
    if n = 1 then
	get a
	put 0
    else

	% normal case, get differences
	get a
	for i : 1 .. n - 1
	    get b
	    diff (i) := b - a
	    a := b
	end for

	% try a pattern length of 1, 2,3... until one works.
	pattern := 1
	loop
	    a := 1
	    loop
		exit when a + pattern > n - 1 or diff (a) not= diff (a + pattern)
		a := a + 1
	    end loop

	    % you've found the correct pattern if a is at the end.
	    % ie. you've tried them all and diif(a) = diff (a+pattern)
	    exit when a + pattern > n - 1

	    % okay, try the next pattern length
	    pattern := pattern + 1
	end loop
	put pattern
    end if
end loop

