---SpringBoot整合Redis实现限流功能
if not KEYS[1] or not ARGV[1] then
    return false;
end
local prefix = "SESSION:";
if KEYS[1] == "IP" then
    prefix = "IP:";
end
local identify = prefix..ARGV[1];
local clientFlow = redis.call("INCR",identify);
if clientFlow == 1 then
    redis.call("EXPIRE",identify,60);
end
if clientFlow <= 30 then
    return true;
else
    return false;
end