package com.sym.oauth2ssoserver.vo;

import com.sym.oauth2ssoserver.entity.SysUser;
import lombok.Data;

import java.util.List;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: as
 * @Package com.sym.oauth2ssoserver.vo
 * @Description: TODO
 * @date 2019/5/16 23:06
 */
@Data
public class SysUserVO extends SysUser {

    /**
     * 权限列表
     */
    private List<String> authorityList;

}
