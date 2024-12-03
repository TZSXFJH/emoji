package com.ustclab.emoji.service.mapper;

import com.ustclab.emoji.common.model.dao.Emoji;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author TZSXFJH
 * @date 2024/11/30
 */
@Mapper
public interface EmojiMapper {
    void insert(String emojiCode, Long userId);

    List<Emoji> getByPage(Long userId);
}
