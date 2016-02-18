% J3: 2010: Punchy
%
% straight forward loop with if's
%

var a, b : int
var instruction : int
var var1, var2 : string (1)
var n : int

a := 0
b := 0

loop
    get instruction
    exit when instruction = 7
    if instruction = 1 then
	get var1
	get n
	if var1 = "A" then
	    a := n
	else
	    b := n
	end if
    elsif instruction = 2 then
	get var1
	if var1 = "A" then
	    put a
	else
	    put b
	end if
    else   % it's 3 4 5 6 (+ * - /)
	get var1
	get var2
	if var1 = "A" then
	    if var2 = "A" then
		if instruction = 3 then
		    a := a + a
		elsif instruction = 4 then
		    a := a * a
		elsif instruction = 5 then
		    a := a - a
		else
		    a := a div a
		end if
	    else
		if instruction = 3 then
		    a := a + b
		elsif instruction = 4 then
		    a := a * b
		elsif instruction = 5 then
		    a := a - b
		else
		    a := a div b
		end if
	    end if
	else
	    if var2 = "A" then
		if instruction = 3 then
		    b := b + a
		elsif instruction = 4 then
		    b := b * a
		elsif instruction = 5 then
		    b := b - a
		else
		    b := b div a
		end if
	    else
		if instruction = 3 then
		    b := b + b
		elsif instruction = 4 then
		    b := b * b
		elsif instruction = 5 then
		    b := b - b
		else
		    b := b div b
		end if
	    end if
	end if
    end if
end loop
