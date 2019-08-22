package com.rnkrsoft.opensource.iam.gateway.notice.domains;

import lombok.Data;

import javax.web.doc.annotation.ApidocElement;
import java.io.Serializable;

/**
 * Created by woate on 2019/7/25.
 */
@Data
public class ReadNoticeRequest implements Serializable{
    @ApidocElement("通知编号")
    String noticeId;
}
