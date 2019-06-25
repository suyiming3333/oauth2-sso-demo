package com.sym.oauth2ssoserver.service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sym.oauth2ssoserver.entity.MyUser;
import com.sym.oauth2ssoserver.entity.SysPermission;
import com.sym.oauth2ssoserver.entity.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: asd
 * @Package com.sym.oauth2ssoserver.service
 * @Description: TODO
 * @date 2019/6/25 22:57
 */

@Slf4j
@Service
public class CustomPreAuthUserDetailsService implements AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userServiceImpl;

    @Autowired
    private PermissionService permissionServiceImpl;

    @Override
    public final UserDetails loadUserDetails(PreAuthenticatedAuthenticationToken token) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken) token.getPrincipal();
        MyUser mu = (MyUser) usernamePasswordAuthenticationToken.getPrincipal();
        String username = mu.getUsername();
        QueryWrapper user =new QueryWrapper<SysUser>();
        user.eq("username",username);
        SysUser sysUser = userServiceImpl.getOne(user);
        if (null == sysUser) {
            log.warn("用户{}不存在", username);
            throw new UsernameNotFoundException(username);
        }
        List<SysPermission> permissionList = permissionServiceImpl.getSysPermissionByUserId(sysUser.getId());
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(permissionList)) {
            for (SysPermission sysPermission : permissionList) {
                authorityList.add(new SimpleGrantedAuthority(sysPermission.getCode()));
            }
        }

        MyUser myUser = new MyUser(sysUser.getUsername(), passwordEncoder.encode(sysUser.getPassword()), authorityList);

        log.info("登录成功！用户: {}", JSON.toJSONString(myUser));

        return myUser;
    }
}