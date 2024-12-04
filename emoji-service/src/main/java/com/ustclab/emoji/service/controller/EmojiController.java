package com.ustclab.emoji.service.controller;

import com.ustclab.emoji.common.model.dao.Emoji;
import com.ustclab.emoji.common.model.dto.EmojiDto;
import com.ustclab.emoji.common.model.dto.UserDto;
import com.ustclab.emoji.common.model.vo.Result;
import com.ustclab.emoji.common.model.vo.ResultCodeEnum;
import com.ustclab.emoji.service.service.EmojiService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author TZSXFJH
 * @date 2024/11/30
 */
@RestController
@RequestMapping(value = "/emoji")
public class EmojiController {
    @Resource
    EmojiService emojiService;

    @PostMapping("post")
    public Result post(@RequestBody EmojiDto emoji) {
        emojiService.post(emoji);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @GetMapping(value = "findByPage/{pageNum}/{pageSize}")
    public Result<List<Emoji>> findByPage(@PathVariable("pageNum") Integer pageNum,
                             @PathVariable("pageSize") Integer pageSize) {
        return Result.build(emojiService.findByPage(pageNum, pageSize),
                ResultCodeEnum.SUCCESS);
    }
}
