package com.ustclab.emoji.manager;

import com.ustclab.emoji.common.utils.TimeUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;

@SpringBootTest
class EmojiManagerApplicationTests {
    @Test
    void testTimeUtil() {

        String[] testDates = {
                "2024-11-30T15:45:30+08:00", // ISO 带时区
                "2024-11-30T15:45:30",       // ISO 本地时间
                "2024-11-30 15:45:30",       // 自定义格式
                "2024/11/30 15:45:30",       // 自定义格式
                "2024-11-30",                // 日期格式
                "20241130154530"             // 紧凑格式
        };

        for (String date : testDates) {
            try {
                Timestamp timestamp = TimeUtil.parseToTimestamp(date);
                System.out.println("Input: " + date + " -> Timestamp: " + timestamp);
            } catch (Exception e) {
                System.err.println("Failed to parse: " + date + " -> " + e.getMessage());
            }
        }
    }


}
