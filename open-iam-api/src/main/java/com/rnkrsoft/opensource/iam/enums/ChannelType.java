package com.rnkrsoft.opensource.iam.enums;

import com.rnkrsoft.interfaces.EnumStringCode;

/**
 * Created by rnkrsoft.com on 2019/7/6.
 */
public enum ChannelType implements EnumStringCode {
    WEB("WEB","WEB"),
    APP("APP","系统");
    String code;
    String desc;

    ChannelType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    public static ChannelType valueOfCode(String code){
        for (ChannelType value : values()){
            if (value.code.equals(code)){
                return value;
            }
        }
        return APP;
    }
}
