package com.rnkrsoft.opensource.iam.enums;

import com.rnkrsoft.interfaces.EnumIntegerCode;
import com.rnkrsoft.interfaces.EnumStringCode;
/**
 * Created by rnkrsoft.com on 2019/7/6.
 */
public enum CityStatus implements EnumIntegerCode {
    DISABLED(0,"禁用"),
    ENABLED(1,"启用"),;

    CityStatus(int code, String desc) {
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

    public static CityStatus valueOfCode(int code){
        for (CityStatus value: values()){
            if(value.getCode() == code){
                return value;
            }
        }
        return DISABLED;
    }
}