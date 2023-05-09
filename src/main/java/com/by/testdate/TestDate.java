package com.by.testdate;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TestDate {
    public static void main(String[] args) throws InterruptedException {
        Date date = new Date();
        String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        Timestamp timeStamp = Timestamp.valueOf(nowTime);
        System.out.println(timeStamp);
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
       // System.out.println(LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String startTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        TimeUnit.SECONDS.sleep(2);
        Duration duration = Duration.between(LocalDateTime.parse(startTime,df),LocalDateTime.now());
        System.out.println(String.valueOf(duration.toMillis()/1000));
    }
}
