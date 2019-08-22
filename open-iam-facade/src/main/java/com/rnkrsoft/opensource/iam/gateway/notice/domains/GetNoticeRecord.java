package com.rnkrsoft.opensource.iam.gateway.notice.domains;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.web.doc.annotation.ApidocElement;
import java.io.Serializable;

/**
 * Created by woate on 2019/7/17.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetNoticeRecord implements Serializable{
    @ApidocElement("通知编号")
    String noticeId;
    @ApidocElement("通知标题")
    String noticeTitle;
    @ApidocElement("通知内容")
    String noticeContent;
}
