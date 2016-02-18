% CCC 2009
% J3: Good Times
%
% use a function to adjust time on 24hr clock
%

function localTime (t : int, c : int) : int
    var lt : int
    lt := t + c
    
    % adjust for the day before or next day
    if lt > 2400 then
        lt := lt - 2400
    elsif lt < 0 then
        lt := lt + 2400
    end if
    
    % adjust for the hour rollover
    if lt mod 100 >= 60 then
        lt := (lt div 100 * 100 + 100) + (lt mod 100 - 60)
    end if
    
    result lt
end localTime

var OttawaTime : int

get OttawaTime
put localTime (OttawaTime, 0), " in Ottawa"
put localTime (OttawaTime, - 300), " in Victoria"
put localTime (OttawaTime, - 200), " in Edmonton"
put localTime (OttawaTime, - 100), " in Winnipeg"
put localTime (OttawaTime, 0), " in Toronto"
put localTime (OttawaTime, 100), " in Halifax"
put localTime (OttawaTime, 130), " in St. John's"


