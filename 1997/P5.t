% CCC 1997
% problem E: Long Division
%
% this is essentially long subtraction! Must be able to do mulitdigit (array)
% subtraction and recognize if negatives are produced. UGLY!

% file handling is used, the number of test cases is given
% then each case is two lines, dividend and divisor.

var infile : string := "div.in"
var outfile : string := "div.out"
var fi, fo : int
var n, ndvd, ndvs, nquo : int
var dvd : array 1 .. 100 of int
var dvs : array 1 .. 100 of int
var quo : array 1 .. 100 of int
var t : array 1 .. 100 of int
var offset, k, m : int
var can : boolean
var line : string
var key : string (1)

open : fi, infile, get
open : fo, outfile, put

get : fi, n
for i : 1 .. n

    % get the dividend and divisor, convert to integer arrays.
    get : fi, line
    for j : 1 .. length (line)
        dvd (j) := strint (line (j))
    end for
    ndvd := length (line)
    get : fi, line
    for j : 1 .. length (line)
        dvs (j) := strint (line (j))
    end for
    
    
    ndvs := length (line)
    nquo := 0
    offset := 0
    loop
        exit when ndvs > ndvd - offset
        nquo := nquo + 1
        quo (nquo) := 0

        loop
            % save dividend
            for j : 1 .. ndvd
                t (j) := dvd (j)
            end for

            % do the division
            can := true
            k := ndvs
            m := ndvs + offset
            loop
                exit when m = 0
                if k >= 1 then
                    if dvd (m) >= dvs (k) then
                        dvd (m) := dvd (m) - dvs (k)
                    else
                        dvd (m) := (dvd (m) + 10) - dvs (k)
                        if m = 1 then
                            can := false
                        else
                            dvd (m - 1) := dvd (m - 1) - 1
                        end if
                    end if
                else
                    if dvd (m) >= 0 then
                        dvd (m) := dvd (m)
                    else
                        dvd (m) := (dvd (m) + 10)
                        if m = 1 then
                            can := false
                        else
                            dvd (m - 1) := dvd (m - 1) - 1
                        end if
                    end if
                end if
                k := k - 1
                m := m - 1
            end loop
            if can then
                quo (nquo) := quo (nquo) + 1
            else
                % restore dividend
                for j : 1 .. ndvd
                    dvd (j) := t (j)
                end for
            end if
            exit when can = false
        end loop
        offset := offset + 1
    end loop

    %print quotient, eliminating leading zeros
    if nquo = 0 then
        put : fo, "0"
        put "0"
    else
        k := 1
        loop
            exit when k >= nquo or quo (k) not= 0
            k := k + 1
        end loop
        loop
            exit when k > nquo
            put : fo, quo (k) ..
            put quo (k) ..
            k := k + 1
        end loop
        put : fo, ""
        put ""
    end if
    
    %print remainder(dividend), eliminating leading zeros
    k := 1
    loop
        exit when k >= ndvd or dvd (k) not= 0
        k := k + 1
    end loop
    loop
        exit when k > ndvd
        put : fo, dvd (k) ..
        put dvd (k) ..
        k := k + 1
    end loop
    put : fo, ""
    put : fo, ""
    put ""
    put ""
end for

close : fi
close : fo

