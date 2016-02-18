% CCC 2002
% problem J2: AmeriCanadian
%
% if a word has > 4 letters and ends with "..kor" 
%                       (k not a vowel: treat "y" as a vowel)
% print the word with "..kour"

% keybd and screen i/o, repeat until "quit!" entered

function convert (s : string) : string
    if length (s) > 4 then
        if s (* - 1.. *) = "or" and
                not (s (* - 2) = "a"
                or s (* - 2) = "e"
                or s (* - 2) = "i"
                or s (* - 2) = "o"
                or s (* - 2) = "u"
                or s (* - 2) = "y") then
            result s (1 .. * - 2) + "our"
        else
            result s
        end if
    else
        result s
    end if
end convert


var s : string
put "Please enter the words to be translated:"
loop
    get s
    exit when s = "quit!"
    put convert (s)
end loop

