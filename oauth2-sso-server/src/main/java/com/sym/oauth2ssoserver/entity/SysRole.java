package com.sym.oauth2ssoserver.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: as
 * @Package com.sym.oauth2ssoserver.entity
 * @Description: TODO
 * @date 2019/5/16 23:00
 */
@Data
public class SysRole implements Serializable {
    private static final long serialVersionUID = -7136537864183138269L;

    private Integer id;

    private String roleName;

    private String roleCode;

    private String roleDescription;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;
}