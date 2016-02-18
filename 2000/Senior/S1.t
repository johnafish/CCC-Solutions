% CCC 2000
% problem J3: Slot Machines

% given a starting number of coins(x), play three machines in turn (a,b,c)
% a pays out 30 coins every 35 turns
% b pays out 60 coins every 100 turns
% c pays out 9 coins every 10 turns
% count the numbers of plays before x = 0

% a big loop until x is zero does the trick.
% (Turing allows for multiple exit whens in a loop 
% so this simplifies the process.)
 
var x, a, b, c : int
var count : int := 0
put "How many quaters does Martha have in the jar:"
get x
put ""
put "How many times has the first machine been played since paying out?"
get a
put ""
put "How many times has the second machine been played since paying out?"
get b
put ""
put "How many times has the third machine been played since paying out?"
get c
put ""

loop
    exit when x = 0
    x := x - 1
    count := count + 1
    a := a + 1
    if a = 35 then
        a := 0
        x := x + 30
    end if
    exit when x = 0
    x := x - 1
    count := count + 1
    b := b + 1
    if b = 100 then
        b := 0
        x := x + 60
    end if
    exit when x = 0
    x := x - 1
    count := count + 1
    c := c + 1
    if c = 10 then
        c := 0
        x := x + 9
    end if
end loop

put "Martha plays ", count, " times before going broke."

