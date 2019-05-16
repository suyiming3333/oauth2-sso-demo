package com.sym.oauth2ssoserver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sym.oauth2ssoserver.entity.SysUser;
import com.sym.oauth2ssoserver.mapper.UserMapper;
import com.sym.oauth2ssoserver.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: UserServiceImpl
 * @Package com.sym.oauth2ssoserver.service.impl
 * @Description: TODO
 * @date 2019/5/16 23:29
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, SysUser> implements UserService {
    @Override
    public SysUser getByUsername(String username) {
        return null;
    }
}
