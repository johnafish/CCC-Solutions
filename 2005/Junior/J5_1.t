% CCC 2005 Junior problem 5
% Bananas
%
% this is an exercise in recursion.
% it involved a pair of mutually recursive methods.
%
% This is quite beyond all but the best "junior" programmers
% and would stump many "senior" high school programmers. :-)

forward function monkeyWord (s : string) : boolean

% an a-word is either:
% the letter A  OR
% the letter B + monkey language word + S
function aWord (s : string) : boolean
    if s = "A" then
        result true
    elsif length (s) >= 3 and s (1) = "B" and
            monkeyWord (s (2 .. * - 1)) and s (*) = "S" then
        result true
    else
        result false
    end if
end aWord

% an monkey language word is either:
% an a-word   or
% an a-word + N + monkey language word
body monkeyWord 
    if aWord (s) then
        result true
    else
        % try all combos for the more complex version
        var found : boolean := false
        for i : 2 .. length (s) - 1        
            found := found or
                (aWord (s (1 .. i - 1)) and
                s (i) = "N" and
                monkeyWord (s (i + 1 .. *)))
        end for
        result found
    end if
end monkeyWord


var s : string
loop
    get s
    exit when s = "X"
    if monkeyWord (s) then
        put "YES"
    else
        put "NO"
    end if
end loop

