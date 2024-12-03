package com.ustclab.emoji.service.controller;

import com.ustclab.emoji.common.model.dto.PasswordDto;
import com.ustclab.emoji.common.model.vo.Result;
import com.ustclab.emoji.common.model.vo.ResultCodeEnum;
import com.ustclab.emoji.common.util.UserLocalUtil;
import com.ustclab.emoji.service.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author TZSXFJH
 * @date 2024/11/30
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    UserService userService;

    @GetMapping("/info/get")
    public Result get() {
        return Result.build(UserLocalUtil.get(), ResultCodeEnum.SUCCESS);
    }

    @PostMapping("/change")
    public Result change(@RequestBody PasswordDto passwordDto) {
        userService.change(passwordDto);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
}
