package com.sym.oauth2ssoserver.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: as
 * @Package com.sym.oauth2ssoserver.entity
 * @Description: TODO
 * @date 2019/5/16 23:01
 */
@Data
public class SysRolePermission implements Serializable {
    private static final long serialVersionUID = 7402412601579098788L;

    private Integer id;

    private Integer roleId;

    private Integer permissionId;
}