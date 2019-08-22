package com.rnkrsoft.opensource.iam.skeleton4j.interfaces.product.services;

import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.product.domains.FetchProductRequest;
import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.product.domains.FetchProductResponse;
import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.product.domains.QueryProductRequest;
import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.product.domains.QueryProductResponse;

import javax.web.doc.annotation.ApidocInterface;
import javax.web.doc.annotation.ApidocService;

/**
 * Created by rnkrsoft.com on 2019/7/7.
 */
@ApidocService("产品管理服务")
public interface ProductService {
    @ApidocInterface("查询")
    QueryProductResponse query(QueryProductRequest request);
    @ApidocInterface("拉取")
    FetchProductResponse fetch(FetchProductRequest request);
}
