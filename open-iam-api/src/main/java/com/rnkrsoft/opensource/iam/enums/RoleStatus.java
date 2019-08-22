package com.rnkrsoft.opensource.iam.enums;

import com.rnkrsoft.interfaces.EnumIntegerCode;

/**
 * Created by rnkrsoft.com on 2019/7/6.
 */
public enum RoleStatus implements EnumIntegerCode {
    DISABLED(0,"禁用"),
    ENABLED(1,"启用"),;

    RoleStatus(int code, String desc) {
        this.desc = desc;
        this.code = code;
    }

    int code;
    String desc;

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    public static RoleStatus valueOfCode(int code){
        for (RoleStatus value: values()){
            if(value.getCode() == code){
                return value;
            }
        }
        return DISABLED;
    }
}