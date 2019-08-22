package com.rnkrsoft.opensource.iam.skeleton4j.interfaces.module.services;

import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.module.domains.*;

import javax.web.doc.annotation.ApidocInterface;
import javax.web.doc.annotation.ApidocService;

/**
 * Created by rnkrsoft.com on 2019/7/10.
 */
@ApidocService("模块服务")
public interface ModuleService {
    @ApidocInterface("导入模块")
    ImportModuleResponse importModule(ImportModuleRequest request);
    @ApidocInterface("查询")
    QueryModuleResponse queryModule(QueryModuleRequest request);
}
