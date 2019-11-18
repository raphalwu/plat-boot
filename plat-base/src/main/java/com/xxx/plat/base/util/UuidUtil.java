package com.xxx.plat.base.util;

import com.xxx.plat.base.twitter.SnowflakeIDGenerator;

import java.util.UUID;

public class UuidUtil {
    private static SnowflakeIDGenerator snowflake = new SnowflakeIDGenerator(0L);

    public UuidUtil() {
    }

    public static long getLongId() {
        return snowflake.getId();
    }

    public static String getLongUUID() {
        return String.valueOf(getLongId());
    }

    public static String getStringUUID() {
        return UUID.randomUUID().toString();
    }
}
