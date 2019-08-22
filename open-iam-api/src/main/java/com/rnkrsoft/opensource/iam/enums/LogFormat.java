package com.rnkrsoft.opensource.iam.enums;

import com.rnkrsoft.interfaces.EnumStringCode;

/**
 * Created by rnkrsoft.com on 2019/7/6.
 */
public enum LogFormat implements EnumStringCode {
    JSON("JSON", "JSON格式"),
    TEXT("TEXT", "TEXT格式");

    LogFormat(String code, String desc) {
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
}