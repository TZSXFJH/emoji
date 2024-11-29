package com.ustclab.emoji.common.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author TZSXFJH
 * @date 2024/11/29
 */
@Data
@Schema(description = "查找用户信息")
public class UserDto {
    @Schema(description = "搜索关键字")
    private String keyword;
    @Schema(description = "最早创建时间")
    private String createTimeBegin;
    @Schema(description = "最晚创建时间")
    private String createTimeEnd;
}
