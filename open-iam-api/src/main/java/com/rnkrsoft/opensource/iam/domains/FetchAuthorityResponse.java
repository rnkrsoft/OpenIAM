package com.rnkrsoft.opensource.iam.domains;

import lombok.*;

import javax.web.doc.AbstractResponse;
import javax.web.doc.annotation.ApidocElement;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class FetchAuthorityResponse extends AbstractResponse {
    @ApidocElement("用户编号")
    String userId;
    @ApidocElement("权限")
    final List<AuthorityRecord> authorities = new ArrayList();
}