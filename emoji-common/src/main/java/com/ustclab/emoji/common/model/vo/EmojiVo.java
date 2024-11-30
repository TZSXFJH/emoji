package com.ustclab.emoji.common.model.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.ustclab.emoji.common.helper.TimestampStringConvert;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author TZSXFJH
 * @date 2024/11/30
 */
@Data
public class EmojiVo {

    @Schema(description = "表情id")
    @ExcelProperty(value = "表情id")
    private Long emojiId;

    @Schema(description = "发送者姓名")
    @ExcelProperty(value = "发送者姓名")
    private String senderName;

    @Schema(description = "表情")
    @ExcelProperty(value = "表情")
    private String emojiCode;

    @Schema(description = "发送时间")
    @ExcelProperty(value = "发送时间", converter = TimestampStringConvert.class)
    private Timestamp time;
}
