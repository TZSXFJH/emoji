package com.ustclab.emoji.manager.service;

import com.ustclab.emoji.common.model.dao.User;
import com.ustclab.emoji.common.model.dto.LoginDto;
import com.ustclab.emoji.common.model.dto.UserDto;
import com.ustclab.emoji.common.model.vo.LoginVo;

import java.util.List;

/**
 * @author TZSXFJH
 * @date 2024/11/27
 */
public interface UserService {
    LoginVo login(LoginDto loginDto);

    void register(User user);

    List<User> findByPage(Integer pageNum, Integer pageSize, UserDto userDto);
}
