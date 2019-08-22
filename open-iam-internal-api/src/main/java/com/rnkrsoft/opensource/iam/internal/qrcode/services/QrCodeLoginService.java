package com.rnkrsoft.opensource.iam.internal.qrcode.services;

import com.rnkrsoft.opensource.iam.internal.qrcode.domains.CheckQrCodeRequest;
import com.rnkrsoft.opensource.iam.internal.qrcode.domains.CheckQrCodeResponse;
import com.rnkrsoft.opensource.iam.internal.qrcode.domains.GenerateQrCodeRequest;
import com.rnkrsoft.opensource.iam.internal.qrcode.domains.GenerateQrCodeResponse;

import javax.web.doc.annotation.ApidocInterface;
import javax.web.doc.annotation.ApidocService;

/**
 * Created by rnkrsoft.com on 2019/7/7.
 * 二维码登陆服务
 */
@ApidocService("二维码登录服务")
public interface QrCodeLoginService {
    @ApidocInterface("生成二维码")
    GenerateQrCodeResponse generate(GenerateQrCodeRequest request);
    @ApidocInterface("检查二维码中的随机用户识别码是否绑定授权")
    CheckQrCodeResponse check(CheckQrCodeRequest request);
}
