package com.rnkrsoft.opensource.iam.domains;

import lombok.*;

import javax.web.doc.annotation.ApidocElement;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class SubAction implements Serializable {
    @ApidocElement("操作编号")
    String subActionCode;
    @ApidocElement("操作名称")
    String subActionName;
    @ApidocElement("操作id")
    Integer subActionId;
}
