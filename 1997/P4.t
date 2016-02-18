% CCC 1997
% problem D: Dynamic Dictionary Coding
%
% compress text, replacing duplicate words with position in a dictionary
% of the words. Non duplicates are put at end of dictionary.
% Size of text indicates a simple array and linear search techniques are
% good enough.

% file handling is used, the number of text sets is given
% then the text sets, separated by a blank line


var infile : string := "ddc.in"
var outfile : string := "ddc.out"
var fi, fo : int
var n, dn, x, k : int
var d : array 1 .. 5000 of string
var line, word : string

open : fi, infile, get
open : fo, outfile, put

get : fi, n
for i : 1 .. n
    dn := 0

    % get lines of text
    loop
        get : fi, line : *
        exit when line = ""

        % get words of line
        loop
            exit when line = ""
            x := index (line, " ")
            if x = 0 then
                word := line
                line := ""
            else
                word := line (1 .. x - 1)
                line := line (x + 1 .. *)
            end if

            %linear search for word
            k := 1
            loop
                exit when k > dn or d (k) = word
                k := k + 1
            end loop

            %print the word or location of word
            if k < dn then
                put : fo, k, " " ..
            else
                dn := dn + 1
                d (dn) := word
                put : fo, word, " " ..
            end if
        end loop
        put : fo, ""
    end loop
    put : fo, ""
end for

close : fi
close : fo





