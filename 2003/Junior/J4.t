% CCC 2003
% Problem J4/S2: Poetry
%
% get the last syllable of 4 lines and determine type of rhyme.
% relatively simple string processing, including converting 
% to lower so that case is not an issue
%
% file I/O
% number of 4 line poems given, followed by those 4 sets of lines
% ouptut is a single word description of each group of 4 lines.

var fi, fo : int
var n : int
var a, b, c, d : string

%returns the last syllable: sequence from the last vowel to end
% or the entire last word if not vowel in that last word.

function lastSyllable (s : string) : string
    var i : int
    var vowels : string := "aeiou "
    i := length (s)
    loop
        exit when i = 0 or index (vowels, s (i)) > 0
        i := i - 1
    end loop
    if i = 0 or s (i) = " " then
        result s (i + 1 .. *)
    else
        result s (i .. *)
    end if
end lastSyllable

% converts the string to lower case
function toLower (s : string) : string
    var t : string := ""
    for i : 1 .. length (s)
        if s (i) < "A" or s (i) > "Z" then
            t := t + s (i)
        else
            t := t + chr (ord (s (i)) - ord ('A') + ord ('a'))
        end if
    end for
    result t
end toLower

open : fi, "poetry1.in", get
open : fo, "poetry1.out", put

get : fi, n
for i : 1 .. n
    get : fi, a : *
    get : fi, b : *
    get : fi, c : *
    get : fi, d : *
    a := lastSyllable (toLower (a))
    b := lastSyllable (toLower (b))
    c := lastSyllable (toLower (c))
    d := lastSyllable (toLower (d))
    if a = b and b = c and c = d then
        put : fo, "perfect"
    elsif a = b and c = d then
        put : fo, "even"
    elsif a = c and b = d then
        put : fo, "cross"
    elsif a = d and b = c then
        put : fo, "shell"
    else
        put : fo, "free"
    end if
end for
close : fi
close : fo

