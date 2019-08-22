package com.rnkrsoft.opensource.iam.internal.qrcode.domains;

import lombok.Data;

import javax.web.doc.AbstractResponse;
import javax.web.doc.annotation.ApidocElement;

/**
 * Created by rnkrsoft.com on 2019/7/7.
 */
@Data
public class GenerateQrCodeResponse extends AbstractResponse{
    @ApidocElement("随机用户识别码")
    String uuid;
}
