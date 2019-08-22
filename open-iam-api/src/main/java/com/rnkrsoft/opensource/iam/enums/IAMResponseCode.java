package com.rnkrsoft.opensource.iam.enums;

import com.rnkrsoft.interfaces.EnumStringCode;

/**
 * Created by rnkrsoft.com on 2019/7/6.
 */
public enum IAMResponseCode implements EnumStringCode {
    USER_NOT_EXISTS("0001","用户不存在"),
    USER_ABNORMAL_STATE("0002","你的帐号处于异常状态"),
    SEND_SMS_TOO_OFTEN("0003","发送短信太频繁"),
    SMS_SERVICE_NOT_CONFIG("0004","短信服务未配置"),
    SEND_SMS_FAILURE("0005","发送短信失败"),
    VALIDATE_SMS_CODE_FAILURE("0006","验证短信验证码失败"),
    SMS_CODE_ERROR("0007","短信验证码错误"),
    SESSION_INVALIDATION("0008","会话失效"),
    DEBUG_MODE("0009","调试模式"),
    WAIT_FOR_AUTHORITY("0010","等待授权"),
    QRCODE_INVALIDATION("0011","二维码已失效"),
    UNSELECT_ROLE("0012","未选择角色"),
    LOGIC_DELETE_FAILURE("0013","删除操作失败"),
    BRANCH_HAS_EXISTS("0014","分支机构已存在"),
    BRANCH_NOT_EXISTS("0015","分支机构不存在"),
    UPDATE_BRANCH_FAILURE("0016","更新分支机构失败"),
    DEPARTMENT_HAS_EXISTS("0014","部门已存在"),
    DEPARTMENT_NOT_EXISTS("0015","部门不存在"),
    UPDATE_DEPARTMENT_FAILURE("0016","更新部门失败"),
    USER_HAS_EXISTS("0001","用户已存在"),
    UPDATE_USER_FAILURE("0001","更新用户失败"),
    APP_HAS_EXISTS("0001","应用已存在"),
    APP_NOT_EXISTS("0001","应用不存在"),
    UPDATE_APP_FAILURE("0001","更新应用失败"),
    JOB_NOT_EXISTS("0001","岗位不存在"),
    FAIL("9999","失败"),
    SUCCESS("0000","成功");
    IAMResponseCode(String code, String desc) {
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

    public static IAMResponseCode valueOfCode(String code){
        for (IAMResponseCode value: values()){
            if(value.getCode().equals(code)){
                return value;
            }
        }
        return SUCCESS;
    }
}
