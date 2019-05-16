package com.sym.oauth2ssoserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sym.oauth2ssoserver.entity.SysUser;
import org.apache.ibatis.annotations.Param;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: UserMapper
 * @Package com.sym.oauth2ssoserver.mapper
 * @Description: TODO
 * @date 2019/5/16 23:31
 */
public interface UserMapper extends BaseMapper<SysUser> {

    IPage<SysUser> getAllUserByPage(Page page, @Param("email") String email);

}
