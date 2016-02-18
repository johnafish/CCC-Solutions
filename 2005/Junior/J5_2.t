% CCC 2005 Junior problem 5
% Bananas
%
% this is a non-recursive version.
% simply go through the string replacing all "ANA" or "BAS"
% with "A". A monkey word will eventually be just "A".
%
% this approach was developed by Jason Jackson of Aurora High School
%

function monkeyWord (t : string) : boolean
    var s : string := t
    var x : int
    loop
        exit when index (s, "ANA") = 0 and index (s, "BAS") = 0
        loop
            exit when index (s, "ANA") = 0
            x := index (s, "ANA")
            s := s (1 .. x - 1) + "A" + s (x + 3 .. *)
        end loop
        loop
            exit when index (s, "BAS") = 0
            x := index (s, "BAS")
            s := s (1 .. x - 1) + "A" + s (x + 3 .. *)
        end loop
    end loop
    result s = "A"
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



