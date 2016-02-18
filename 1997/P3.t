% CCC 1997
% problem C: Double Knockout Competition
%
% you've got: undefeated, 1-loss and eliminated teams
% at each step: 
%     1/2 the 1-loss become eliminated
% and 1/2 the undefeated become 1-loss

% in other words: eliminated = eliminated + (1/2 1-loss teams)
%                 1 - loss   = 1-loss  - (1/2 1-loss teams) + (1/2 of the undefeated)
%                 undefeated = undefeated - (1/2 undefeated)

% except at end with 1 undefeated and 1 1-loss
%                          they play and the undefeated loses!
% then they play and you're done: there is 1 l-loss team.

% file handling is used, the number of starting teams is given
% then the number of teams themselves


var infile : string := "dkc.in"
var outfile : string := "dkc.out"
var fi, fo : int
var n, x, ud, ol, el, count : int

open : fi, infile, get
open : fo, outfile, put

get : fi, n
for i : 1 .. n
    get : fi, x
    ud := x
    ol := 0
    el := 0
    count := 0
    loop
        put : fo, "Round: ", count, ": ", ud, " undefeated, ", ol,
            " one-loss, ", el, " eliminated"
        exit when ud = 0 and ol = 1
        if ud = 1 and ol = 1 then
            el := el
            ol := 2
            ud := 0
        else
            el := el + ol div 2
            ol := ol - (ol div 2) + (ud div 2)
            ud := ud - (ud div 2)
        end if
        count := count + 1
    end loop
    put : fo, "There are ", count, " rounds."
    put : fo, ""
end for

close : fi
close : fo





