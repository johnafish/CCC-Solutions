% CCC 1998
% problem A: Censor
%
% replace all 4 letter words with ****

% file handling is used, the number of lines is given

var infile : string := "censor.in"
var outfile : string := "censor.out"
var fi, fo : int
var n, x : int
var line, word : string

open : fi, infile, get
open : fo, outfile, put

get : fi, n
for i : 1 .. n
    get : fi, line : *
    
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

        if length (word) = 4 then
            put : fo, "**** " ..
            put "**** " ..
        else
            put : fo, word, " " ..
            put word, " " ..
        end if
    end loop
    put : fo, ""
    put : fo, ""
    put ""
    put ""
end for

close : fi
close : fo





