package com.rnkrsoft.opensource.iam.gateway.user.domains;

import com.rnkrsoft.opensource.iam.enums.YesOrNo;
import com.rnkrsoft.platform.protocol.TokenAble;
import lombok.Data;

import javax.web.doc.annotation.ApidocElement;
import java.io.Serializable;

/**
 * Created by woate on 2019/7/14.
 */
@Data
public class SearchUserRequest implements Serializable, TokenAble{
    @ApidocElement("关键字，支持用户姓名拼音，用户姓名汉字，手机号，昵称等")
    String keyword;
    @ApidocElement(value = "是否支持模糊搜索", enumClass = YesOrNo.class, defaults = "1")
    int fuzzy;
    String token;
}
