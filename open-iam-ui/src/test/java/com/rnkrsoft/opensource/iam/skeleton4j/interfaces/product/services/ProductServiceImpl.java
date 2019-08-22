package com.rnkrsoft.opensource.iam.skeleton4j.interfaces.product.services;

import com.rnkrsoft.opensource.iam.enums.ProductStatus;
import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.application.domains.QueryApplicationRecord;
import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.product.domains.*;

import javax.web.data.Node;
import javax.web.skeleton4j.utils.QueryUtils;

/**
 * Created by rnkrsoft.com on 2019/7/9.
 */
public class ProductServiceImpl implements ProductService{
    @Override
    public QueryProductResponse query(QueryProductRequest request) {
        QueryProductResponse response = new QueryProductResponse();
        QueryUtils.parameter(request, request.getPageSize(), 1, response);
        for (int i = 0; i < request.getPageSize(); i++) {
            response.addRecord(QueryProductRecord.builder()
                    .productId(i)
                    .productCode("" + i)
                    .productTitle("应用系统" + i)
                    .applicationTitle("应用系统" + i)
                    .productStatus(ProductStatus.ENABLED.getCode())
                    .build());
        }
        return response;
    }

    @Override
    public FetchProductResponse fetch(FetchProductRequest request) {
        FetchProductResponse response = new FetchProductResponse();
        response.addNode(Node.builder().text("产品1").value("1").build());
        response.addNode(Node.builder().text("产品2").value("2").build());
        return response;
    }
}
