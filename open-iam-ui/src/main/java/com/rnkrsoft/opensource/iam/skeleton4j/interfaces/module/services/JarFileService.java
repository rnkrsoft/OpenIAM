package com.rnkrsoft.opensource.iam.skeleton4j.interfaces.module.services;

import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.module.domains.GenerateModuleRequest;
import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.module.domains.GenerateModuleResponse;

import javax.web.doc.annotation.ApidocInterface;
import javax.web.doc.annotation.ApidocService;

/**
 * Created by rnkrsoft.com on 2019/7/11.
 */
@ApidocService("架包服务")
public interface JarFileService {
    @ApidocInterface("生成模块")
    GenerateModuleResponse generateModule(GenerateModuleRequest request);
}
