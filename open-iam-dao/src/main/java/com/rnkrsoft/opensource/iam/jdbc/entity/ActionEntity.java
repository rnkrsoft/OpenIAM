package com.rnkrsoft.opensource.iam.jdbc.entity;

import com.rnkrsoft.framework.orm.PrimaryKey;
import com.rnkrsoft.framework.orm.PrimaryKeyStrategy;
import com.rnkrsoft.framework.orm.jdbc.*;
import com.rnkrsoft.opensource.iam.enums.ActionStatus;
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
@Table(name = "ACTION", prefix = "TB", suffix = "INFO")
@Comment("功能")
public class ActionEntity extends BaseEntity implements Serializable {
    @Comment("功能主键")
    @PrimaryKey(strategy = PrimaryKeyStrategy.IDENTITY)
    @NumberColumn(name = "ACTION_ID", nullable = false, type = NumberType.INTEGER)
    Integer actionId;

    @Comment("功能代码")
    @StringColumn(name = "ACTION_CODE", nullable = true, type = StringType.VARCHAR)
    String actionCode;

    @Comment("功能标题")
    @StringColumn(name = "ACTION_TITLE", nullable = true, type = StringType.VARCHAR)
    String actionTitle;

    @Comment("应用编号")
    @StringColumn(name = "PRODUCT_ID", nullable = true, type = StringType.VARCHAR)
    String productId;

    @Comment("状态")
    @NumberColumn(name = "ACTION_STATUS", nullable = true, type = NumberType.BYTE, enumClass = ActionStatus.class, defaultValue = "1")
    Integer actionStatus;

}
