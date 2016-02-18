% CCC 2007
% J4: Anagram Checker
%
% Determine is two phrases are anagrams of each other.
%
% let s and t be the two strings
% For each letter in s, find it in t
%    if not found, not an anagram
%    if found, replace it with a " "
%        (so it can't be found a second time)
% if you've done the entire loop,
%      its an anagram. (provided t is all spaces)
%

var s, t : string
var x, i : int
var bad : boolean

put "Enter first phrase> " ..
get s : *
put "Enter second phrase> " ..
get t : *

bad := false
i := 1
loop
    exit when i > length (s) or bad
    if s (i) not= " " then
        x := index (t, s (i))
        if x = 0 then
            bad := true
            put "Is not an anagram."
        else
            t := t (1 .. x - 1) + " " + t (x + 1 .. *)
        end if
    end if
    i := i + 1
end loop
if not bad then
    for j : 1 .. length (t)
        bad := bad or t (j) not= " "
    end for
    if not bad then
        put "Is an anagram."
    else
        put "Is not an anagram."
    end if
end if

