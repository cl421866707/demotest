package cn.my3gods.demotest;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.Test;

public class LocalDateTimeTest {

    @Test
    public void testLocalDateTimeToTimeMillis() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSSS");
        LocalDateTime now = LocalDateTime.now();
        String formatNow = now.format(dateTimeFormatter);
        System.err.println(formatNow);

        ZonedDateTime zonedDateTime = now.atZone(ZoneId.systemDefault());
        Instant instant = zonedDateTime.toInstant();
        long zoneMillis = instant.toEpochMilli();
        System.err.println(zoneMillis);

        long currentTimeMillis = System.currentTimeMillis();
        System.err.println(currentTimeMillis);
    }

    @Test
    public void testLocalDateTimeFormate(){
        //新的格式化API中，格式化后的结果都默认是String，有时我们也需要返回经过格式化的同类型对象
        LocalDateTime ldt1 = LocalDateTime.now();
        DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String temp = dtf1.format(ldt1);
        LocalDateTime formatedDateTime = LocalDateTime.parse(temp, dtf1);
        System.out.println(formatedDateTime);
    }
}
