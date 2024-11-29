package com.ustclab.emoji.manager.controller;

import com.ustclab.emoji.common.model.dao.User;
import com.ustclab.emoji.common.model.dto.LoginDto;
import com.ustclab.emoji.common.model.vo.LoginVo;
import com.ustclab.emoji.common.model.vo.Result;
import com.ustclab.emoji.common.model.vo.ResultCodeEnum;
import com.ustclab.emoji.manager.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * @author TZSXFJH
 * @date 2024/11/27
 */
@Tag(name = "用户接口")
@RestController
@RequestMapping(value = "/admin/system/index")
public class IndexController {
    @Resource
    private UserService userService;

    @Operation(
            summary = "登录方法"
//            responses = {
//                    @ApiResponse(
//                            responseCode = "200",
//                            description = "响应成功",
//                            content = @Content(
//                                    mediaType = "application/json"
//                            )
//                    )
//            }
    )
    @PostMapping("login")
    public Result login(@RequestBody LoginDto loginDto) {
        LoginVo loginVo = userService.login(loginDto);
        return Result.build(loginVo, ResultCodeEnum.SUCCESS);
    }

    @Operation(
            summary = "注册方法"
    )
    @PostMapping("register")
    public Result register(@RequestBody User user) {
        userService.register(user);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @GetMapping("logout")
    public Result logout(@RequestHeader("token") String token) {
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
}
