package com.ustclab.emoji.manager.controller;

import com.ustclab.emoji.common.model.dto.UserDto;
import com.ustclab.emoji.common.model.vo.Result;
import com.ustclab.emoji.common.model.vo.ResultCodeEnum;
import com.ustclab.emoji.manager.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * @author TZSXFJH
 * @date 2024/11/29
 */
@Tag(name = "用户管理")
@RestController
@RequestMapping(value = "/admin/user")
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping(value = "findByPage/{pageNum}/{pageSize}")
    public Result findByPage(@PathVariable("pageNum") Integer pageNum,
                             @PathVariable("pageSize") Integer pageSize,
                             UserDto userDto) {
        return Result.build(userService.findByPage(pageNum, pageSize, userDto),
                ResultCodeEnum.SUCCESS);
    }
}
