package com.ustclab.emoji.common.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author TZSXFJH
 * @date 2024/11/27
 */
@Data
@Schema(description = "用户登录请求参数")
public class LoginDto {
    @Schema(description = "用户名")
    private String userName;
    @Schema(description = "密码")
    private String password;

}
