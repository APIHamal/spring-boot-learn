---基于redis和lua脚本实现的分布式令牌桶限流
--[[
    KEYS[1]流控业务token
    ARGV[1]当前时间戳
    ARGV[2]桶内令牌初始容量
    ARGV[3]桶内令牌容量上限
    ARGV[4]生成令牌时间间隔(MS)
    CUR_TOKENS当前桶内可用令牌数量
]]--
local BUSINESS_TOKEN = KEYS[1];
local CUR_TIME = ARGV[1];
local INIT_CAPACITY = ARGV[2];
local CAPACITY_LIMIT = ARGV[3];
local PRE_TOKEN_TIME = ARGV[4];
local CUR_TOKENS;
--初始化流控数据结构
local bucket = redis.call("HGETALL",BUSINESS_TOKEN);
if #bucket == 0 then
    --初始化业务桶数据
    redis.call("HMSET",BUSINESS_TOKEN,"TOKENS",INIT_CAPACITY,"LAST_REFILL_TIME",CUR_TIME);
    CUR_TOKENS = INIT_CAPACITY;
else
    --计算流控时间差添加令牌数据
    local timeDiff = CUR_TIME - bucket['LAST_REFILL_TIME'];
    if timeDiff >= PRE_TOKEN_TIME then
        local tokenDiff = math.floor(timeDiff / PRE_TOKEN_TIME);
        if tokenDiff > 0 then
            CUR_TOKENS = bucket['TOKENS'] + tokenDiff;
            -------判断时间间隔
        end
    end
end