package com.rnkrsoft.opensource.iam.skeleton4j.pages.iam;

import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.city.services.CityService;
import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.module.services.ModuleService;

import javax.web.skeleton4j.annotation.WebImport;
import javax.web.skeleton4j.annotation.WebNamespace;
import javax.web.skeleton4j.annotation.WebPage;
import javax.web.skeleton4j.enums.WebGui;
import javax.web.skeleton4j.enums.WebMode;

/**
 * Created by rnkrsoft.com on 2019/7/7.
 */
@WebPage(priority =10,
        displayName = "模块管理",
        author = "master",
        namespaces = {
                @WebNamespace(
                        imports = {
                                @WebImport(gui = WebGui.FORM, mode = WebMode.QUERY, serviceClass = ModuleService.class, value = "queryModule")
                        }
                )
        }
)
public class ModuleManagePage {
}
