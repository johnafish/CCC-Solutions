% CCC 2006 Junior problem 1
% The New CCC (Canadian Calorie Counting)
%
% simple I/O and if's

var burger, side, drink, dessert : int
var calorie : int

put "Welcome to Chip's Fast Food Emporium"
put "Please enter your burger choice: "
get burger
put "Please enter your side order choice: "
get side
put "Please enter your drink choice: "
get drink
put "Please enter your desert choice: "
get dessert

if burger = 1 then
    calorie := 461
elsif burger = 2 then
    calorie := 431
elsif burger = 3 then
    calorie := 420
else
    calorie := 0
end if

if side = 1 then
    calorie := calorie + 100
elsif side = 2 then
    calorie := calorie + 57
elsif side = 3 then
    calorie := calorie + 70
else
    calorie := calorie + 0
end if

if drink = 1 then
    calorie := calorie + 130
elsif drink = 2 then
    calorie := calorie + 160
elsif drink = 3 then
    calorie := calorie + 118
else
    calorie := calorie + 0
end if

if dessert = 1 then
    calorie := calorie + 167
elsif dessert = 2 then
    calorie := calorie + 266
elsif dessert = 3 then
    calorie := calorie + 75
else
    calorie := calorie + 0
end if

put ""
put "Your total Calorie count is ", calorie, "."

