package com.ustclab.emoji.common.exception;

import com.ustclab.emoji.common.model.vo.ResultCodeEnum;
import lombok.Data;

/**
 * @author TZSXFJH
 * @date 2024/11/27
 */
@Data
public class EmojiException extends RuntimeException{
    private Integer code;
    private String message;
    private ResultCodeEnum resultCodeEnum;

    public EmojiException(ResultCodeEnum resultCodeEnum) {
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMessage();
        this.resultCodeEnum = resultCodeEnum;
    }
}
