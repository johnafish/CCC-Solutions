% CCC 2002
% Problem S1J3: Student Council's Breakfast

% given 4 numbers and a total
% print all combinations of those numbers (multiplied by something)
% which give the total
% also print the # of combinations
% print the minimum of the "multiple by" numbers

% keybd and screen i/o

var pink, gren, redc, orange, total : int
var tp, tg, tr, tor, mini, count : int

put "Cost of PINK tickets:"
get pink
put "Cost of GREEN tickets:"
get gren
put "Cost of RED tickets:"
get redc
put "Cost of ORANGE tickets:"
get orange
put "How much must be raised with ticket sales:"
get total
put "combination are"

mini := 999999
count := 0
for a : 0 .. total div pink
    tp := a * pink
    for b : 0 .. total div gren
        tg := b * gren
        for c : 0 .. total div redc
            tr := c * redc
            for d : 0 .. total div orange
                tor := d * orange
                if tp + tg + tr + tor = total then
                    if a + b + c + d < mini then
                        mini := a + b + c + d
                    end if
                    count := count + 1
                    put "# of PINK is ", a, "   # of GREEN is ", b,
                        "   #of RED is ", c, "   # of ORANGE is ", d
                end if
            end for
        end for
    end for
end for

put "Total combinations is ", count, "."
put "Minimum number of tickets to print is ", mini, "."
