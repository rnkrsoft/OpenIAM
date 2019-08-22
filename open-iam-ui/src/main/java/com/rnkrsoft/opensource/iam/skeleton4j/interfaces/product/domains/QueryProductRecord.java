package com.rnkrsoft.opensource.iam.skeleton4j.interfaces.product.domains;

import com.rnkrsoft.opensource.iam.enums.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.web.doc.annotation.ApidocElement;
import java.io.Serializable;

/**
 * Created by rnkrsoft.com on 2019/7/8.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QueryProductRecord implements Serializable{
    @ApidocElement(value = "产品编号", maxLen = 20, unique = true, hidden = true)
    Integer productId;

    @ApidocElement(value = "产品代码", maxLen = 36)
    String productCode;

    @ApidocElement(value = "应用名称", maxLen = 20)
    String applicationTitle;

    @ApidocElement(value = "产品名称", maxLen = 36)
    String productTitle;

    @ApidocElement(value = "产品状态", maxLen = 10, enumClass = ProductStatus.class)
    Integer productStatus;
}
