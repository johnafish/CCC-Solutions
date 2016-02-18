% CCC 1997
% problem B: Nasty Numbers
%
% a nasty number has a pair of factors that when subtracted
% equals a pair of factors that add together

% observation: thinking of 36
% the pairs are:
% 1, 36
% 2, 18
% 3, 12
% etc. as the 1st factor increases the diff AND sum decrease

% therfore starting at 1, find the difference and then look
% for the sum DOWN the list!
% they only get smaller so they are easy to find. (no arrays needed)

% file handling is used, the number of numbers is given
% then the numbers themselves

function nasty (x : int) : boolean
    var f1, f2 : int
    var s : real := sqrt (x)
    var diff : int

    f1 := 1
    loop
        exit when f1 > s
        
        % find the next factor
        loop
            exit when f1 > s or x mod f1 = 0
            f1 := f1 + 1
        end loop
        if f1 < s then
            diff := (x div f1) - f1
            f2 := f1 + 1
        
            % find pairs after the first pair with a sum >= diff
            % (stop if the sum is less than the diff)
            loop
                loop
                    exit when f2 > s or x mod f2 = 0
                    f2 := f2 + 1
                end loop
                exit when f2 > s or (x div f2) + f2 <= diff
                f2 := f2 + 1
            end loop
            
            % if you found it great
            if f2 < s and x div f2 + f2 = diff then
                result true
            end if
        end if
        
        % otherwise keep going
        f1 := f1 + 1
    end loop
    
    % if you never find any pairs: false
    result false
end nasty

var infile : string := "nasty.in"
var outfile : string := "nasty.out"
var fi, fo : int
var n, x : int

open : fi, infile, get
open : fo, outfile, put

get : fi, n
for i : 1 .. n
    get : fi, x
    if nasty (x) then
        put : fo, x, " is nasty"
        put x, " is nasty"
    else
        put : fo, x, " is not nasty"
        put x, " is not nasty"
    end if
end for

close : fi
close : fo





