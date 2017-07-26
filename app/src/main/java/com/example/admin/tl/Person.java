package com.example.admin.tl;

import cn.bmob.v3.BmobObject;

/**
 * Created by admin on 2017/7/20.
 */

public class Person extends BmobObject {

    public String name;

    public String  getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
