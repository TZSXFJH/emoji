package com.ustclab.emoji.service.service.impl;

import com.github.pagehelper.PageHelper;
import com.ustclab.emoji.common.model.dao.Emoji;
import com.ustclab.emoji.common.model.dto.EmojiDto;
import com.ustclab.emoji.common.util.UserLocalUtil;
import com.ustclab.emoji.service.mapper.EmojiMapper;
import com.ustclab.emoji.service.service.EmojiService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author TZSXFJH
 * @date 2024/11/30
 */
@Service
public class EmojiServiceImpl implements EmojiService {
    @Resource
    EmojiMapper emojiMapper;
    @Override
    public void post(EmojiDto emoji) {
        emojiMapper.insert(emoji.getEmojiCode(), UserLocalUtil.get().getUserId());
    }

    @Override
    public List<Emoji> findByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return emojiMapper.getByPage(UserLocalUtil.get().getUserId());
    }
}
