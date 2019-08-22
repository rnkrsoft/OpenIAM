package com.rnkrsoft.opensource.iam.enums;

import com.rnkrsoft.interfaces.EnumIntegerCode;

/**
 * Created by rnkrsoft.com on 2019/7/6.
 */
public enum ResourceType implements EnumIntegerCode {
    SKELETON4J(1,"Skeleton4j"),
    RESOURCE(2,"资源"),;

    ResourceType(int code, String desc) {
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

    public static ResourceType valueOfCode(int code){
        for (ResourceType value: values()){
            if(value.getCode() == code){
                return value;
            }
        }
        return SKELETON4J;
    }
}