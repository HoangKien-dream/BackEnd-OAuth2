package com.example.springoauth2.service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;

public class Oauth2Helper {
    public String generateToke() {
        String accessToken = UUID.randomUUID().toString();
        return accessToken;
    }

    public long expiredTime() {
        ZoneId zoneId = ZoneId.systemDefault();
        long date = LocalDateTime.now().plusDays(7).atZone(zoneId).toEpochSecond();
        return date;
    }
}
