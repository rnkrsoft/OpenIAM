package com.rnkrsoft.opensource.iam.skeleton4j.pages.iam;

import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.authority.services.AuthorityService;

import javax.web.skeleton4j.annotation.WebImport;
import javax.web.skeleton4j.annotation.WebNamespace;
import javax.web.skeleton4j.annotation.WebPage;
import javax.web.skeleton4j.enums.WebGui;
import javax.web.skeleton4j.enums.WebMode;

/**
 * Created by rnkrsoft.com on 2019/7/7.
 */
@WebPage(priority = 2,
        displayName = "权限管理",
        author = "master",
        namespaces = {
                @WebNamespace(
                        imports = {
                                @WebImport(displayName = "查询", gui = WebGui.FORM, mode = WebMode.QUERY, serviceClass = AuthorityService.class, value = "query")
                        }
                )
        })
public class AuthorityPage {
}
