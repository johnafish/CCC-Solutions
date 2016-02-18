% CCC 2006 Junior problem 4
% Its tough being a teen
%
% given a set of ordered intructions determine the
% order it which to do them.
%
% this involves array processing
% before = the task which must be done first (before)
% after = the task which must be done second (after)
% taken = true/false depending whether ith element has been done
% done = the final order to do the tasks (a string)

% to determine the order
% cycle thru the numbers 1 to 7 and find first one that can be done.
% if one can be done, add it (get out of loop) and start again at 1
%                                                    (seven times)
% if at any point you get past 7, then the entire thing can't be done.



var before, after : array 1 .. 15 of int
var taken : array 1 .. 15 of boolean
var done : string
var x, y : int
var size : int
var i, k : int
var stop, cantdoit : boolean

% a task can be done if it is never in "after"
% or all the tasks before it are done.
function canBeDone (i : int) : boolean
    var ok : boolean
    var neverafter : boolean
    var taskbeforedone : boolean
    ok := not taken (i)
    if ok then
        neverafter := true
        for j : 1 .. size
            if after (j) = i then
                neverafter := false
            end if
        end for
        if not neverafter then
            taskbeforedone := true
            for j : 1 .. size
                if after (j) = i and not taken (before (j)) then
                    taskbeforedone := false
                end if
            end for
            ok := taskbeforedone
        end if
    end if
    result ok
end canBeDone



% get the constraints
before (1) := 1
after (1) := 7
before (2) := 1
after (2) := 4
before (3) := 2
after (3) := 1
before (4) := 3
after (4) := 4
before (5) := 3
after (5) := 5
size := 5
loop
    get x, y
    exit when x = 0 and y = 0
    size := size + 1
    before (size) := x
    after (size) := y
end loop
for j : 1 .. 7
    taken (j) := false
end for
done := ""

% get the order
k := 1
cantdoit := false
loop
    exit when cantdoit or k > 7
    i := 1
    stop := false
    loop
        exit when i > 7 or stop or cantdoit
        if not taken (i) and canBeDone (i) then
            taken (i) := true
            done := done + intstr (i) + " "
            stop := true
        end if
        i := i + 1
        if i > 7 and not stop then
            done := "Cannot complete these tasks.  Going to bed."
            cantdoit := true
        end if
    end loop
    k := k + 1
end loop
put done

