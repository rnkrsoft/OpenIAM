package com.rnkrsoft.opensource.iam.domains;

import lombok.Builder;
import lombok.Data;

import javax.web.doc.annotation.ApidocElement;
import java.io.Serializable;

/**
 * Created by rnkrsoft.com on 2019/7/7.
 */
@Data
@Builder
public class FetchApplicationRequest implements Serializable{
    @ApidocElement("用户号")
    String userId;
}
