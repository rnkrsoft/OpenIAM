package com.rnkrsoft.opensource.iam.gateway.notice.domains;

import lombok.Data;

import javax.web.doc.AbstractResponse;
import javax.web.doc.annotation.ApidocElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by woate on 2019/7/17.
 */
@Data
public class GetNoticeResponse extends AbstractResponse{
    @ApidocElement("记录")
    final List<GetNoticeRecord> records = new ArrayList();
}
