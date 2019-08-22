package com.rnkrsoft.opensource.iam.domains;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.web.doc.annotation.ApidocElement;
import java.io.Serializable;

/**
 * Created by rnkrsoft.com on 2019/7/12.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetUserRequest implements Serializable{
    @ApidocElement("用户编号")
    String userId;
}
