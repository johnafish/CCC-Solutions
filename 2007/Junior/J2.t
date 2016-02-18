% CCC 2007 J2: Speak TXTMSG
%
%  Simple if else condition and a loop

var s : string
loop
    put "Enter phrase> " ..
    get s
    if s = "CU" then
        put "see you"
    elsif s = ":-)" then
        put "I'm happy"
    elsif s = ":-(" then
        put "I'm unhappy"
    elsif s = ";-)" then
        put "wink"
    elsif s = ":-P" then
        put "stick out my tongue"
    elsif s = "(~.~)" then
        put "sleepy"
    elsif s = "TA" then
        put "totally awesome"
    elsif s = "CCC" then
        put "Canadian Computing Competition"
    elsif s = "CUZ" then
        put "because"
    elsif s = "TY" then
        put "thank-you"
    elsif s = "YW" then
        put "you're welcome"
    elsif s = "TTYL" then
        put "talk to you later"
    else
        put s
    end if
    exit when s = "TTYL"
end loop

