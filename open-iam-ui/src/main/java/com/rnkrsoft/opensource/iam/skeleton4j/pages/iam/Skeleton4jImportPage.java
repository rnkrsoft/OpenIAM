package com.rnkrsoft.opensource.iam.skeleton4j.pages.iam;

import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.module.services.JarFileService;
import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.module.services.ModuleService;

import javax.web.skeleton4j.annotation.WebImport;
import javax.web.skeleton4j.annotation.WebNamespace;
import javax.web.skeleton4j.annotation.WebPage;
import javax.web.skeleton4j.annotation.WebResource;
import javax.web.skeleton4j.enums.InterfaceCall;
import javax.web.skeleton4j.enums.WebGui;

/**
 * Created by rnkrsoft.com on 2019/7/10.
 */
@WebPage(priority = 12,
        displayName = "模块导入",
        author = "master",
        apiDocGenerateGui = false,
        resources = {
                @WebResource("classpath*:com.rnkrsoft.opensource.iam.skeleton4j.skeleton4jImport.html")
        },
        namespaces = {
                @WebNamespace(
                        imports = {
                                @WebImport(gui = WebGui.NONE, serviceClass = JarFileService.class, value = "generateModule")
                        },
                        call = InterfaceCall.LOCAL
                )
        }
)
public class Skeleton4jImportPage {
}
