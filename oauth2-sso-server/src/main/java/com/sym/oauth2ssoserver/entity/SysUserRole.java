package com.sym.oauth2ssoserver.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: as
 * @Package com.sym.oauth2ssoserver.entity
 * @Description: TODO
 * @date 2019/5/16 23:03
 */
@Data
public class SysUserRole implements Serializable {
    private static final long serialVersionUID = -1810195806444298544L;

    private Integer id;

    private Integer userId;

    private Integer roleId;
}