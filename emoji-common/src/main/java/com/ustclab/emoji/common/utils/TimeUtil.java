package com.ustclab.emoji.common.utils;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author TZSXFJH
 * @date 2024/11/30
 */
public class TimeUtil {
    // 定义可能的时间格式
    private static final List<DateTimeFormatter> FORMATTERS = new ArrayList<>();

    static {
        FORMATTERS.add(DateTimeFormatter.ISO_ZONED_DATE_TIME);  // ISO 格式带时区
        FORMATTERS.add(DateTimeFormatter.ISO_LOCAL_DATE_TIME); // ISO 本地时间
        FORMATTERS.add(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")); // 自定义格式
        FORMATTERS.add(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")); // 另一个格式
        FORMATTERS.add(DateTimeFormatter.ofPattern("yyyy-MM-dd"));          // 仅日期
        FORMATTERS.add(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));      // 紧凑格式
        // 添加更多格式根据需求
    }

    public static Timestamp parseToTimestamp(String dateTimeString) throws Exception{
        for (DateTimeFormatter formatter : FORMATTERS) {
            try {
                if (formatter.equals(DateTimeFormatter.ISO_ZONED_DATE_TIME)) {
                    // 带时区的处理
                    ZonedDateTime zdt = ZonedDateTime.parse(dateTimeString, formatter);
                    return Timestamp.from(zdt.toInstant());
                } else if (formatter.toString().equals(DateTimeFormatter.ofPattern("yyyy-MM-dd").toString())) {
                    // 仅日期的处理
                    LocalDate date = LocalDate.parse(dateTimeString, formatter);
                    return Timestamp.valueOf(date.atStartOfDay());
                } else {
                    // 不带时区的处理
                    LocalDateTime ldt = LocalDateTime.parse(dateTimeString, formatter);
                    return Timestamp.valueOf(ldt);
                }
            } catch (DateTimeParseException ignored) {
                // 捕获异常并尝试下一个格式
            }
        }
        throw new Exception();
    }
}
