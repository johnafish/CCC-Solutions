% CCC 1996
% problem 4 Calculate as the Romans Do

% convert to and from Roman Numerals to do addition
% max number is 1000

% file i/o used.
% the first number is the number of numbers.
% expression is in the form "a+b=" where a and b are Roman Numerals

function toDecimal (s : string) : int
    var t, v : int
    var old : int := 100000
    t := 0
    for i : 1 .. length (s)
        if s (i) = "I" then
            v := 1
        elsif s (i) = "V" then
            v := 5
        elsif s (i) = "X" then
            v := 10
        elsif s (i) = "L" then
            v := 50
        elsif s (i) = "C" then
            v := 100
        elsif s (i) = "D" then
            v := 500
        elsif s (i) = "M" then
            v := 1000
        end if
        if v > old then
            t := t + v - 2 * old
        else
            t := t + v
        end if
        old := v
    end for
    result t
end toDecimal

% very unFancy: brute force is easiest
function toRoman (xx : int) : string
    var x : int := xx
    var d : int
    var s : string
    s := ""
    d := x div 100
    x := x mod 100
    if d = 1 then
        s := s + "C"
    elsif d = 2 then
        s := s + "CC"
    elsif d = 3 then
        s := s + "CCC"
    elsif d = 4 then
        s := s + "CD"
    elsif d = 5 then
        s := s + "D"
    elsif d = 6 then
        s := s + "DC"
    elsif d = 7 then
        s := s + "DCC"
    elsif d = 8 then
        s := s + "DCCC"
    elsif d = 9 then
        s := s + "CM"
    end if
    d := x div 10
    x := x mod 10
    if d = 1 then
        s := s + "X"
    elsif d = 2 then
        s := s + "XX"
    elsif d = 3 then
        s := s + "XXX"
    elsif d = 4 then
        s := s + "XL"
    elsif d = 5 then
        s := s + "L"
    elsif d = 6 then
        s := s + "LX"
    elsif d = 7 then
        s := s + "LXX"
    elsif d = 8 then
        s := s + "LXXX"
    elsif d = 9 then
        s := s + "XC"
    end if
    d := x
    if d = 1 then
        s := s + "I"
    elsif d = 2 then
        s := s + "II"
    elsif d = 3 then
        s := s + "III"
    elsif d = 4 then
        s := s + "IV"
    elsif d = 5 then
        s := s + "V"
    elsif d = 6 then
        s := s + "VI"
    elsif d = 7 then
        s := s + "VII"
    elsif d = 8 then
        s := s + "VIII"
    elsif d = 9 then
        s := s + "IX"
    end if
    result s
end toRoman


var infile : string := "rom.in"
var outfile : string := "rom.out"
var fi, fo : int
var n : int
var line : string
var plus, answer : int

open : fi, infile, get
open : fo, outfile, put

get : fi, n
for i : 1 .. n
    get : fi, line
    plus := index (line, "+")
    answer := toDecimal (line (1 .. plus - 1)) + toDecimal (line (plus +
        1 .. length (line) - 1))
    if answer > 1000 then
        put : fo, line, "CONCORDIA CUM VERITATE"
        put line, "CONCORDIA CUM VERITATE"
    else
        put : fo, line, toRoman (answer)
        put line, toRoman (answer)
    end if
    put : fo, ""
    put ""
end for

close : fi
close : fo

