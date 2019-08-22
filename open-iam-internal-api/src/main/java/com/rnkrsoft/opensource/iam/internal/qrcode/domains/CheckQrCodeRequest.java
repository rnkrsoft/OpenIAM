package com.rnkrsoft.opensource.iam.internal.qrcode.domains;

import lombok.Builder;
import lombok.Data;

import javax.web.doc.annotation.ApidocElement;
import java.io.Serializable;

/**
 * Created by rnkrsoft.com on 2019/7/8.
 */
@Data
@Builder
public class CheckQrCodeRequest implements Serializable{
    @ApidocElement("用户随机识别码")
    String uuid;
    @ApidocElement("已登陆用户TOKEN")
    String token;
}
