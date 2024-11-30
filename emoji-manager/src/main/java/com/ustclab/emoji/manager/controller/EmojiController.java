package com.ustclab.emoji.manager.controller;

import com.ustclab.emoji.common.model.vo.Result;
import com.ustclab.emoji.common.model.vo.ResultCodeEnum;
import com.ustclab.emoji.manager.service.EmojiService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author TZSXFJH
 * @date 2024/11/30
 */
@RestController
@RequestMapping(value = "/admin/emoji")
public class EmojiController {
    @Resource
    EmojiService emojiService;
    @GetMapping("/count")
    public Result<Map<String, Integer>> count(String startTime,
                                              String endTime) {
        return Result.build(emojiService.count(startTime, endTime), ResultCodeEnum.SUCCESS);
    }

    @GetMapping("/hourly")
    public Result<List<Map<String, Integer>>> hourly(String startTime) {
        return Result.build(emojiService.hourly(startTime), ResultCodeEnum.SUCCESS);
    }

}
