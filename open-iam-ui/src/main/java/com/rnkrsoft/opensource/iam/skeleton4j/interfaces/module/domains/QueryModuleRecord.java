package com.rnkrsoft.opensource.iam.skeleton4j.interfaces.module.domains;

import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.module.services.ModuleService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.web.doc.annotation.ApidocElement;
import javax.web.skeleton4j.annotation.WebCascadeInterface;
import java.io.Serializable;

/**
 * Created by rnkrsoft.com on 2019/7/10.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QueryModuleRecord implements Serializable{
    @ApidocElement(value = "流水号", maxLen = 36, unique = true, interfaces = {
            @WebCascadeInterface(serviceClass = ModuleService.class, value = "importModule", confirm = true)
    })
    Integer serialNo;
    @ApidocElement(value = "文件名", maxLen = 40)
    String fileName;
    @ApidocElement(value = "导入日期", maxLen = 20)
    String importDate;
}
