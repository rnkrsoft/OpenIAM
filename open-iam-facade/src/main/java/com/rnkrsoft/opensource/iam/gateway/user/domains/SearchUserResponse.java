package com.rnkrsoft.opensource.iam.gateway.user.domains;

import lombok.Data;

import javax.web.doc.AbstractResponse;
import javax.web.doc.annotation.ApidocElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by woate on 2019/7/14.
 */
@Data
public class SearchUserResponse extends AbstractResponse{
    @ApidocElement("搜索结果")
    final List<SearchUserRecord> records = new ArrayList();
}
