package com.rnkrsoft.opensource.iam.jdbc.entity;

import com.rnkrsoft.framework.orm.PrimaryKey;
import com.rnkrsoft.framework.orm.PrimaryKeyStrategy;
import com.rnkrsoft.framework.orm.jdbc.*;
import com.rnkrsoft.opensource.iam.enums.ProductStatus;
import lombok.*;

import java.io.Serializable;

/**
 * rnkrsoft.com 框架自动生成!
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "PRODUCT", prefix = "TB", suffix = "INFO")
@Comment("产品")
public class ProductEntity extends BaseEntity implements Serializable {
    @Comment("产品主键")
    @PrimaryKey(strategy = PrimaryKeyStrategy.IDENTITY)
    @NumberColumn(name = "PRODUCT_ID", nullable = false, type = NumberType.INTEGER)
    Integer productId;

    @Comment("产品代码")
    @StringColumn(name = "PRODUCT_CODE", nullable = true, type = StringType.VARCHAR)
    String productCode;

    @Comment("产品标题")
    @StringColumn(name = "PRODUCT_TITLE", nullable = true, type = StringType.VARCHAR)
    String productTitle;

    @Comment("应用编码")
    @StringColumn(name = "APP_ID", nullable = true, type = StringType.VARCHAR)
    String appId;

    @Comment("产品状态")
    @NumberColumn(name = "PRODUCT_STATUS", nullable = true, type = NumberType.BYTE, enumClass = ProductStatus.class, defaultValue = "1")
    Integer productStatus;

}
