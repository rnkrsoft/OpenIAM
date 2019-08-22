package com.rnkrsoft.opensource.iam.skeleton4j.interfaces.module.domains;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.web.doc.annotation.ApidocElement;
import javax.web.skeleton4j.upload.UploadFileAble;
import java.io.File;
import java.io.Serializable;

/**
 * Created by rnkrsoft.com on 2019/7/10.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GenerateModuleRequest implements Serializable, UploadFileAble{
    @ApidocElement(value = "接口包名", required = false)
    String interfacePackages;
    @ApidocElement(value = "页面包名", required = false)
    String pagePackages;
    @ApidocElement(value = "应用代码", required = false)
    String appCode;
    String uploadFile;
}
