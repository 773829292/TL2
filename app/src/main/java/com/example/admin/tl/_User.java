package com.example.admin.tl;

import cn.bmob.v3.BmobObject;

/**
 * Created by admin on 2017/7/19.
 */

public class _User extends BmobObject {

    private String username;

    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
