package com.rnkrsoft.opensource.iam.domains;

import lombok.Data;

import javax.web.doc.AbstractResponse;
import javax.web.doc.annotation.ApidocElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rnkrsoft.com on 2019/7/7.
 */
@Data
public class FetchApplicationResponse extends AbstractResponse{
    @ApidocElement("记录")
    final List<ApplicationInfo> records = new ArrayList();
}
