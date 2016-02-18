% CCC 2008
% J4: Prefix to postfix
%
% It occurs to me that the best way to do this
% is with recursion.
%
% this method takes an input prefix string s, 
% and creates the postfix, stored in post1
% with a temporary string called rest
%
% generally the method is, if the postfix starts with + or -
% the first operand starts in the third letter
% the second operand is found from the rest of the string
%     (after the first operand is taken out)
% then put the postfix together: "first second +"
%
% If the postfix starts with a number, that is the postfix. 
%
% an example might help, starting with "+ - 1 2 3"
% 1. it would enter with s = "+ - 1 2 3"
% 2. it would enter again with s = "- 1 2 3"
% 3. it would enter again with "1 2 3" and leave with post1="1" and rest="2 3"
%          (the "1" is the first part of "- 1 2 3")
% 4. it would enter again with "2 3" and leave with post1="2" and rest="3"
%          (the "2" is the second part of "- 1 2 3")
%    now 2 finishes with post1 = "1 2 -" and rest = "3"
%    now the first half of 1 is done and second call is done:
% 5. it would enter with s = "3" and leave with post1 = "3" and rest = ""
% now 1 is done with post1 = "1 2 - 3 +"
%
% think about it enough and you'll get it :-)
%


var prefix : string
var post1, temp : string

procedure postfix (s : string, var post1 : string, var rest : string)
    var first, second, temp1, temp2 : string
    put "in:" + s
    if s (1) = '+' then
        postfix (s (3 .. *), first, temp1)
        postfix (temp1, second, temp2)
        post1 := first + " " + second + " +"
        rest := temp2
    elsif s (1) = '-' then
        postfix (s (3 .. *), first, temp1)
        postfix (temp1, second, temp2)
        post1 := first + " " + second + " -"
        rest := temp2
    else
        post1 := s (1)
        if length (s) > 1 then
            rest := s (3 .. *)
        else
            rest := ""
        end if
    end if
    put "out:" + post1 + ":" + rest
end postfix

loop
    get prefix : *
    exit when prefix = "0"
    postfix (prefix, post1, temp)
    put post1
end loop
