package com.rnkrsoft.opensource.iam.gateway.facades;

import com.rnkrsoft.opensource.iam.gateway.notice.domains.*;
import com.rnkrsoft.opensource.iam.gateway.notice.facade.NoticeFacade;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by woate on 2019/7/17.
 */
@Service
public class NoticeFacadeImpl implements NoticeFacade {
    @Override
    public GetNoticeResponse getNotice(GetNoticeRequest request) {
        GetNoticeResponse response = new GetNoticeResponse();
        response.getRecords().add(GetNoticeRecord.builder()
                .noticeId(UUID.randomUUID().toString())
                .noticeTitle("系统测试1")
                .noticeContent("系统测试系统测试1")
                .build()
        );
        response.getRecords().add(GetNoticeRecord.builder()
                        .noticeId(UUID.randomUUID().toString())
                        .noticeTitle("系统测试2")
                        .noticeContent("系统测试系统测试2")
                        .build()
        );
        return response;
    }

    @Override
    public ReadNoticeResponse readNotice(ReadNoticeRequest request) {
        ReadNoticeResponse response = new ReadNoticeResponse();
        return response;
    }
}
