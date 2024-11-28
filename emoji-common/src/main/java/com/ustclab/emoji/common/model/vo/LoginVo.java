package com.ustclab.emoji.common.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author TZSXFJH
 * @date 2024/11/27
 */
@Schema(description = "用户登录返回数据")
@Data
public class LoginVo {
    @Schema(description = "令牌")
    private String token;
}
