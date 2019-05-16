package com.sym.oauth2ssoserver.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sym.oauth2ssoserver.entity.SysPermission;
import com.sym.oauth2ssoserver.mapper.PermissionMapper;
import com.sym.oauth2ssoserver.service.PermissionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: PermissionServiceImpl
 * @Package com.sym.oauth2ssoserver.service.impl
 * @Description: TODO
 * @date 2019/5/16 23:33
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, SysPermission> implements PermissionService {
    @Override
    public List<SysPermission> findByUserId(Integer userId) {
        return null;
    }
}
