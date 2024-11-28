package com.ustclab.emoji.common.handler;

import com.ustclab.emoji.common.exception.EmojiException;
import com.ustclab.emoji.common.model.vo.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author TZSXFJH
 * @date 2024/11/27
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result error(Exception e) {
        e.printStackTrace();
        return Result.build(null, 201, "出现异常");
    }

    @ExceptionHandler(value = EmojiException.class)
    @ResponseBody
    public Result error(EmojiException e) {
        e.printStackTrace();
        return Result.build(null, e.getResultCodeEnum());
    }
}
