package com.rnkrsoft.opensource.iam.enums;

import com.rnkrsoft.interfaces.EnumStringCode;
/**
 * Created by rnkrsoft.com on 2019/7/6.
 */
public enum YesOrNo implements EnumStringCode {
    Y("1","是"),
    N("2","否"),;

    YesOrNo(String code, String desc) {
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

    public static YesOrNo valueOfCode(String code){
        for (YesOrNo value: values()){
            if(value.getCode().equals(code)){
                return value;
            }
        }
        return N;
    }
}