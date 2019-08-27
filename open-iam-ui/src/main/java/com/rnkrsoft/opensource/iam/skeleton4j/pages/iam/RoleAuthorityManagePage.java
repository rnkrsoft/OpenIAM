package com.rnkrsoft.opensource.iam.skeleton4j.pages.iam;

import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.application.services.ApplicationService;
import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.authority.services.AuthorityService;
import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.role.services.RoleService;

import javax.web.skeleton4j.annotation.WebImport;
import javax.web.skeleton4j.annotation.WebNamespace;
import javax.web.skeleton4j.annotation.WebPage;
import javax.web.skeleton4j.annotation.WebResource;
import javax.web.skeleton4j.enums.WebGui;

/**
 * Created by rnkrsoft.com on 2019/7/9.
 */
@WebPage(priority = 5,
        displayName = "角色权限配置",
        author = "master",
        apiDocGenerateGui = false,
        resources = {
                @WebResource("classpath:com.rnkrsoft.opensource.iam.authority.authority.html")
        },
        namespaces = {
                @WebNamespace(
                        imports = {
                                @WebImport(gui = WebGui.NONE, serviceClass = AuthorityService.class, value = "fetchAuthorityByRole=fetchAuthorityByRole"),
                                @WebImport(gui = WebGui.NONE, serviceClass = AuthorityService.class, value = "batchUpdateByRole"),
                                @WebImport(gui = WebGui.NONE, serviceClass = ApplicationService.class, value = "fetchApp=fetch"),
                                @WebImport(gui = WebGui.NONE, serviceClass = RoleService.class, value = "fetchRole=fetch")
                        }
                )
        }
)
public class RoleAuthorityManagePage {
}
