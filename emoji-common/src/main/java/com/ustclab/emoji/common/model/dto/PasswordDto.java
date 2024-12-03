package com.ustclab.emoji.common.model.dto;

import lombok.Data;

/**
 * @author TZSXFJH
 * @date 2024/11/30
 */
@Data
public class PasswordDto {
    private String oldPassword;
    private String newPassword;
}
