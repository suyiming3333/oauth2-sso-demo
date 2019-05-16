package com.sym.oauth2ssoserver.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author suyiming3333@gmail.com
 * @version V1.0
 * @Title: asd
 * @Package com.sym.oauth2ssoserver.entity
 * @Description: TODO
 * @date 2019/5/16 23:02
 */
@Data
public class SysUser implements Serializable {
    private static final long serialVersionUID = 5872438942257394882L;

    private Integer id;

    private String username;

    private String password;

    private String nickname;

    private String email;

    private Integer status = 0;

    private String createUser;


    private Date createTime;

    private String updateUser;

    private Date updateTime;
}
