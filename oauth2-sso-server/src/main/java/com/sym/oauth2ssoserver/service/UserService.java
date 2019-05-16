package com.sym.oauth2ssoserver.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sym.oauth2ssoserver.entity.SysUser;
import org.springframework.stereotype.Service;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: UserService
 * @Package com.sym.oauth2ssoserver.service
 * @Description: TODO
 * @date 2019/5/16 22:55
 */

public interface UserService extends IService<SysUser> {
    SysUser getByUsername(String username);
}
