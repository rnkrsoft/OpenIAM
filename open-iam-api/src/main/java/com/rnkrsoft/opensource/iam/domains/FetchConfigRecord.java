package com.rnkrsoft.opensource.iam.domains;

import lombok.Builder;
import lombok.Data;

import javax.web.doc.AbstractResponse;

/**
 * Created by rnkrsoft.com on 2019/7/8.
 */
@Data
@Builder
public class FetchConfigRecord extends AbstractResponse{
    String name;
    String value;
}
