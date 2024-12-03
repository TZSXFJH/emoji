package com.ustclab.emoji.service.service;

import com.ustclab.emoji.common.model.dao.Emoji;
import com.ustclab.emoji.common.model.dto.EmojiDto;

import java.util.List;

/**
 * @author TZSXFJH
 * @date 2024/11/30
 */
public interface EmojiService {

    void post(EmojiDto emoji);

    List<Emoji> findByPage(Integer pageNum, Integer pageSize);
}
