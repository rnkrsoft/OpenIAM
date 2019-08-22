package com.rnkrsoft.opensource.iam.enums;

import com.rnkrsoft.interfaces.EnumStringCode;

/**
 * Created by rnkrsoft.com on 2019/7/6.
 */
public enum UserSource implements EnumStringCode {
    PARTNER("PARTNER","合作伙伴"),
    EMPLOYEE("EMPLOYEE","雇员");

    UserSource(String code, String desc) {
        this.desc = desc;
        this.code = code;
    }

    String code;
    String desc;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    public static UserSource valueOfCode(String code){
        for (UserSource value: values()){
            if(value.getCode().equals(code)){
                return value;
            }
        }
        return EMPLOYEE;
    }
}