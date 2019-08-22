package com.rnkrsoft.opensource.iam.services;

import com.rnkrsoft.framework.orm.Pagination;
import com.rnkrsoft.opensource.iam.enums.ProductStatus;
import com.rnkrsoft.opensource.iam.jdbc.bo.QueryProductBO;
import com.rnkrsoft.opensource.iam.jdbc.dao.ProductDAO;
import com.rnkrsoft.opensource.iam.jdbc.entity.ProductEntity;
import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.product.domains.*;
import com.rnkrsoft.opensource.iam.skeleton4j.interfaces.product.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.web.data.Node;
import javax.web.skeleton4j.utils.QueryUtils;
import java.util.List;

/**
 * Created by rnkrsoft.com on 2019/7/9.
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductDAO productDAO;

    @Override
    public QueryProductResponse query(QueryProductRequest request) {
        QueryProductResponse response = new QueryProductResponse();
        Pagination<QueryProductBO> pagination = new Pagination<QueryProductBO>(request.getPageSize(), request.getPageNo());
        productDAO.queryProduct(pagination);
        QueryUtils.parameter(request, pagination.getTotal(), pagination.getPageNum(), response);
        for (QueryProductBO record: pagination.getRecords()) {
            response.addRecord(QueryProductRecord.builder()
                    .productId(record.getProductId())
                    .productCode(record.getProductCode())
                    .productTitle(record.getProductTitle())
                    .applicationTitle(record.getAppTitle())
                    .productStatus(record.getProductStatus())
                    .build());
        }
        return response;
    }

    @Override
    public FetchProductResponse fetch(FetchProductRequest request) {
        FetchProductResponse response = new FetchProductResponse();
        List<ProductEntity> productEntities = productDAO.selectAnd(ProductEntity.builder().productStatus(ProductStatus.ENABLED.getCode()).build());
        for (ProductEntity record : productEntities){
            response.addNode(Node.builder().text(record.getProductTitle()).value(Integer.toString(record.getProductId())).build());
        }
        return response;
    }
}
