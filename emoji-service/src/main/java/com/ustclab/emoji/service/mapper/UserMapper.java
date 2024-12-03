package com.ustclab.emoji.service.mapper;

import com.ustclab.emoji.common.model.dao.User;
import com.ustclab.emoji.common.model.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author TZSXFJH
 * @date 2024/11/27
 */
@Mapper
public interface UserMapper {
    User getByUserName(String userName);

    void insert(User user);

    void update(Long userId, String newPassword);
}