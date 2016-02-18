% CCC 2002
% problem J1: 0123456789
%
% print the digits using series of 3 "*"'s
% Gross solution: 
%  copy paste and create unique print solution for each of 10 digits

% keybd and screen i/o


var n : int
put "Enter a digit between 0 and 9:"
get n
if n = 0 then
    put " * * * "
    put "*     *"
    put "*     *"
    put "*     *"
    put "       "
    put "*     *"
    put "*     *"
    put "*     *"
    put " * * * "
elsif n = 1 then
    put "       "
    put "      *"
    put "      *"
    put "      *"
    put "       "
    put "      *"
    put "      *"
    put "      *"
    put "       "
elsif n = 2 then
    put " * * * "
    put "      *"
    put "      *"
    put "      *"
    put " * * * "
    put "*      "
    put "*      "
    put "*      "
    put " * * * "
elsif n = 3 then
    put " * * * "
    put "      *"
    put "      *"
    put "      *"
    put " * * * "
    put "      *"
    put "      *"
    put "      *"
    put " * * * "
elsif n = 4 then
    put "       "
    put "*     *"
    put "*     *"
    put "*     *"
    put " * * * "
    put "      *"
    put "      *"
    put "      *"
    put "       "
elsif n = 5 then
    put " * * * "
    put "*      "
    put "*      "
    put "*      "
    put " * * * "
    put "      *"
    put "      *"
    put "      *"
    put " * * * "
elsif n = 6 then
    put " * * * "
    put "*      "
    put "*      "
    put "*      "
    put " * * * "
    put "*     *"
    put "*     *"
    put "*     *"
    put " * * * "
elsif n = 7 then
    put " * * * "
    put "      *"
    put "      *"
    put "      *"
    put "       "
    put "      *"
    put "      *"
    put "      *"
    put "       "
elsif n = 8 then
    put " * * * "
    put "*     *"
    put "*     *"
    put "*     *"
    put " * * * "
    put "*     *"
    put "*     *"
    put "*     *"
    put " * * * "
elsif n = 9 then
    put " * * * "
    put "*     *"
    put "*     *"
    put "*     *"
    put " * * * "
    put "      *"
    put "      *"
    put "      *"
    put " * * * "
end if

