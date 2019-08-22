package com.rnkrsoft.opensource.iam.enums;

import com.rnkrsoft.interfaces.EnumIntegerCode;

/**
 * Created by rnkrsoft.com on 2019/7/6.
 */
public enum SexType implements EnumIntegerCode {
    UNKNOWN(-1,"未知性别"),
    MAN(1,"男性"),
    WOMAN(2,"女性");

    SexType(int code, String desc) {
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

    public static SexType valueOfCode(int code){
        for (SexType value: values()){
            if(value.getCode() == code){
                return value;
            }
        }
        return UNKNOWN;
    }
}