package com.ustclab.emoji.manager.mapper;

import com.ustclab.emoji.common.model.dao.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author TZSXFJH
 * @date 2024/11/27
 */
@Mapper
public interface UserMapper {
    User getByUserName(String userName);

    void insert(User user);
}
