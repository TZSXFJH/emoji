package com.ustclab.emoji.manager.mapper;

import com.ustclab.emoji.common.model.dao.Emoji;
import org.apache.ibatis.annotations.Mapper;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author TZSXFJH
 * @date 2024/11/30
 */
@Mapper
public interface EmojiMapper {

    List<Emoji> get(Timestamp startTimestamp, Timestamp endTimestamp);
}
