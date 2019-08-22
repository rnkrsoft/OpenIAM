package com.rnkrsoft.opensource.iam.gateway.notice.facade;

import com.rnkrsoft.opensource.iam.gateway.notice.domains.GetNoticeRequest;
import com.rnkrsoft.opensource.iam.gateway.notice.domains.GetNoticeResponse;
import com.rnkrsoft.opensource.iam.gateway.notice.domains.ReadNoticeRequest;
import com.rnkrsoft.opensource.iam.gateway.notice.domains.ReadNoticeResponse;

import javax.web.doc.annotation.ApidocInterface;
import javax.web.doc.annotation.ApidocService;

/**
 * Created by woate on 2019/7/17.
 */
@ApidocService(value = "通知服务", channel = "iam")
public interface NoticeFacade {
    @ApidocInterface(value = "获取通知", name = "601", version = "1")
    GetNoticeResponse getNotice(GetNoticeRequest request);
    @ApidocInterface(value = "读取通知", name = "602", version = "1")
    ReadNoticeResponse readNotice(ReadNoticeRequest request);
}
