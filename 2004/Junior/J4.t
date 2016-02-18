% 2004 J4: Encryption
%
% String processing and encryption using a keyword to shift.
%  - need to cycle through the keyword letters
%    ('A' shifts 0, 'B' shifts 1, etc.)
%  - if the resulting letter (after the shift) is beyond 'Z'
%    need to bring it back 
%    (eg if it is 4 beyond Z, it should be a 'D' (ie. 'A' + 3)
%

var key, plain : string
var i, l : int

get key
get plain : *
i := 1
for j : 1 .. length (plain)
    if plain (j) >= "A" and plain (j) <= "Z" then
        l := ord (plain (j)) + (ord (key (i)) - ord ('A'))
        if l > ord ('Z') then
            l := ord ('A') + (l - ord ('Z') - 1)
        end if
        put chr (l) ..
        i := i + 1
        if i > length (key) then
            i := 1
        end if
    end if
end for

