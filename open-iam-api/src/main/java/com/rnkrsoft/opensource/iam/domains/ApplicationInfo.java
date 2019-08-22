package com.rnkrsoft.opensource.iam.domains;

import lombok.*;

import javax.web.doc.annotation.ApidocElement;
import java.io.Serializable;

/**
 * Created by rnkrsoft.com on 2019/7/7.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ApplicationInfo implements Serializable{
    @ApidocElement("系统编号")
    String appCode;
    @ApidocElement("系统名称")
    String appTitle;
    @ApidocElement("系统图标")
    String appIcon;
    @ApidocElement("是否拥有该权限")
    boolean owner=false;
    @ApidocElement("系统地址")
    String appUrl;
}
