package com.ustclab.emoji.service.service.impl;

import com.ustclab.emoji.common.exception.EmojiException;
import com.ustclab.emoji.common.model.dao.User;
import com.ustclab.emoji.common.model.dto.LoginDto;
import com.ustclab.emoji.common.model.dto.PasswordDto;
import com.ustclab.emoji.common.model.vo.LoginVo;
import com.ustclab.emoji.common.model.vo.Result;
import com.ustclab.emoji.common.model.vo.ResultCodeEnum;
import com.ustclab.emoji.common.util.JwtUtil;
import com.ustclab.emoji.common.util.UserLocalUtil;
import com.ustclab.emoji.service.mapper.UserMapper;
import com.ustclab.emoji.service.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author TZSXFJH
 * @date 2024/11/27
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userMapper;

    @Override
    public LoginVo login(LoginDto loginDto) {
        String userName = loginDto.getUserName();
        User user = userMapper.getByUserName(userName);
        if(user == null) {
            throw new EmojiException(ResultCodeEnum.LOGIN_ERROR);
        }
        if(!DigestUtils.md5DigestAsHex(loginDto.getPassword().getBytes()).equals(user.getPassword())) {
            throw new EmojiException(ResultCodeEnum.LOGIN_ERROR);
        }
        // 生成Token
        String token = JwtUtil.createToken(user);
        LoginVo loginVo = new LoginVo();
        loginVo.setToken(token);
        return loginVo;
    }

    @Override
    public void register(User user) {
        User getUser = userMapper.getByUserName(user.getUserName());
        if (getUser != null) {
            throw new EmojiException(ResultCodeEnum.USER_NAME_IS_EXISTS);
        }
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        userMapper.insert(user);
    }

    @Override
    public void change(PasswordDto passwordDto) {
        String password = UserLocalUtil.get().getPassword();
        if(!DigestUtils.md5DigestAsHex(passwordDto.getOldPassword().getBytes()).equals(password)){
            throw new EmojiException(ResultCodeEnum.PASSWORD_ERROR);
        }
        userMapper.update(UserLocalUtil.get().getUserId() ,passwordDto.getNewPassword());
        //TODO
        //
    }

    @GetMapping("/userInfo/get")
    public Result getUserInfo() {
        return Result.build(UserLocalUtil.get(), ResultCodeEnum.SUCCESS);
    }
}
