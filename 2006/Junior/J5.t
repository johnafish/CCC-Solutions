% CCC 2006 Junior problem 5
% CCC Othello
%
% the main othello board is the 2d array b
% 0 = empty
% 1 = white
% 2 = black

var b : array 1 .. 8, 1 .. 8 of int
var config : string
var n, row, col : int
var player : string := "b"
var countb, countw : int
var f : string (1)

%for testing only
procedure print
    for r : 1 .. 8
        for c : 1 .. 8
            if b (r, c) = 0 then
                put " " ..
            elsif b (r, c) = 1 then
                put "w" ..
            else
                put "b" ..
            end if
        end for
        put ""
    end for
end print

%starting at r,c look in all directions to reverse markers
% first determine if a marker of the same color exists
% in a direction, if it does, flip the others in between
procedure reverse (r, c : int)
    var i, j, k : int
    var found : boolean
    if b (r, c) = 1 then
        k := 2
    else
        k := 1
    end if

    %up
    i := r - 1
    found := false
    loop
        exit when i = 0 or found or b (i, c) = 0
        if b (i, c) = b (r, c) then
            found := true
        end if
        i := i - 1
    end loop
    if found then
        i := r - 1
        loop
            exit when b (i, c) = b (r, c)
            b (i, c) := b (r, c)
            i := i - 1
        end loop
    end if

    %down
    i := r + 1
    found := false
    loop
        exit when i = 9 or found or b (i, c) = 0
        if b (i, c) = b (r, c) then
            found := true
        end if
        i := i + 1
    end loop
    if found then
        i := r + 1
        loop
            exit when b (i, c) = b (r, c)
            b (i, c) := b (r, c)
            i := i + 1
        end loop
    end if

    %left
    i := c - 1
    found := false
    loop
        exit when i = 0 or found or b (r, i) = 0
        if b (r, i) = b (r, c) then
            found := true
        end if
        i := i - 1
    end loop
    if found then
        i := c - 1
        loop
            exit when b (r, i) = b (r, c)
            b (r, i) := b (r, c)
            i := i - 1
        end loop
    end if

    %right
    i := c + 1
    found := false
    loop
        exit when i = 9 or found or b (r, i) = 0
        if b (r, i) = b (r, c) then
            found := true
        end if
        i := i + 1
    end loop
    if found then
        i := c + 1
        loop
            exit when b (r, i) = b (r, c)
            b (r, i) := b (r, c)
            i := i + 1
        end loop
    end if

    %up left
    i := r - 1
    j := c - 1
    found := false
    loop
        exit when i = 0 or j = 0 or found or b (i, j) = 0
        if b (i, j) = b (r, c) then
            found := true
        end if
        i := i - 1
        j := j - 1
    end loop
    if found then
        i := r - 1
        j := c - 1
        loop
            exit when b (i, j) = b (r, c)
            b (i, j) := b (r, c)
            i := i - 1
            j := j - 1
        end loop
    end if

    %up right
    i := r - 1
    j := c + 1
    found := false
    loop
        exit when i = 0 or j = 9 or found or b (i, j) = 0
        if b (i, j) = b (r, c) then
            found := true
        end if
        i := i - 1
        j := j + 1
    end loop
    if found then
        i := r - 1
        j := c + 1
        loop
            exit when b (i, j) = b (r, c)
            b (i, j) := b (r, c)
            i := i - 1
            j := j + 1
        end loop
    end if

    %down left
    i := r + 1
    j := c - 1
    found := false
    loop
        exit when i = 9 or j = 0 or found or b (i, j) = 0
        if b (i, j) = b (r, c) then
            found := true
        end if
        i := i + 1
        j := j - 1
    end loop
    if found then
        i := r + 1
        j := c - 1
        loop
            exit when b (i, j) = b (r, c)
            b (i, j) := b (r, c)
            i := i + 1
            j := j - 1
        end loop
    end if

    %down right
    i := r + 1
    j := c + 1
    found := false
    loop
        exit when i = 9 or j = 9 or found or b (i, j) = 0
        if b (i, j) = b (r, c) then
            found := true
        end if
        i := i + 1
        j := j + 1
    end loop
    if found then
        i := r + 1
        j := c + 1
        loop
            exit when b (i, j) = b (r, c)
            b (i, j) := b (r, c)
            i := i + 1
            j := j + 1
        end loop
    end if
end reverse

% get setup
for r : 1 .. 8
    for c : 1 .. 8
        b (r, c) := 0
    end for
end for
get config
if config = "a" then
    b (4, 4) := 1
    b (5, 5) := 1
    b (4, 5) := 2
    b (5, 4) := 2
elsif config = "b" then
    for r : 1 .. 8
        b (r, r) := 2
        b (r, 9 - r) := 1
    end for
elsif config = "c" then
    for r : 1 .. 8
        b (r, 3) := 2
        b (r, 4) := 2
        b (r, 5) := 1
        b (r, 6) := 1
    end for
end if

%play game
get n
for i : 1 .. n
    get row, col % assuming valid moves
    if player = "b" then
        b (row, col) := 2
        player := "w"
    else
        b (row, col) := 1
        player := "b"
    end if
    reverse (row, col)
end for

%do counting
countb := 0
countw := 0
for r : 1 .. 8
    for c : 1 .. 8
        if b (r, c) = 1 then
            countw := countw + 1
        elsif b (r, c) = 2 then
            countb := countb + 1
        end if
    end for
end for
put countb, " ", countw



