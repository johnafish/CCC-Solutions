% CCC 2008
% J1: Body Mass Index
%
% BMI = w / (h*h)
%
% BMI > 25 = Overweight
% BMI 18.5 - 25 = normal weight
% BMI < 18.5 = Underweight

var BMI, weight, height : real

put "Enter weight: " ..
get weight
put "Enter height: " ..
get height

BMI := weight / (height * height)
if BMI > 25 then
    put "Overweight"
elsif BMI < 18.5 then
    put "Underweight"
else
    put "Normal weight"
end if

