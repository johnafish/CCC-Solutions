% CCC 1997
% problem A: Sentences
%
% given arrays of nouns, verb and object
% print ALL combinations of nouns, vers and objects
% a simple simple triple nested loop does that.

% file handling is used, the number of sets is given
% then the number of subjects, verbs and objects (max 20)
% finally the subjects, verbs and objects, each on a separate line

var infile : string := "sent.in"
var outfile : string := "sent.out"
var fi, fo : int
var n : int
var ns, nv, no : int
var s, v, o : array 1 .. 20 of string

open : fi, infile, get
open : fo, outfile, put

get : fi, n
for i : 1 .. n
    get : fi, ns
    get : fi, nv
    get : fi, no
    for j : 1 .. ns
        get : fi, s (j) : *
    end for
    for j : 1 .. nv
        get : fi, v (j) : *
    end for
    for j : 1 .. no
        get : fi, o (j) : *
    end for
    for j : 1 .. ns
        for k : 1 .. nv
            for m : 1 .. no
                put : fo, s (j), " ", v (k), " ", o (m), "."
            end for
        end for
    end for
    put :fo, ""
end for

close : fi
close : fo





