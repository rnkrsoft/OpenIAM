package com.rnkrsoft.opensource.iam.jdbc.entity;

import com.rnkrsoft.framework.orm.PrimaryKey;
import com.rnkrsoft.framework.orm.PrimaryKeyStrategy;
import com.rnkrsoft.framework.orm.jdbc.*;
import com.rnkrsoft.opensource.iam.enums.SubActionStatus;
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
@Table(name = "SUB_ACTION", prefix = "TB", suffix = "INFO")
@Comment("子功能")
public class SubActionEntity extends BaseEntity implements Serializable {
    @Comment("子功能主键")
    @PrimaryKey(strategy = PrimaryKeyStrategy.IDENTITY)
    @NumberColumn(name = "SUB_ACTION_ID", nullable = false, type = NumberType.INTEGER)
    Integer subActionId;

    @Comment("子功能代码")
    @StringColumn(name = "SUB_ACTION_CODE", nullable = true, type = StringType.VARCHAR)
    String subActionCode;

    @Comment("子功能标题")
    @StringColumn(name = "SUB_ACTION_TITLE", nullable = true, type = StringType.VARCHAR)
    String subActionTitle;

    @Comment("功能编号")
    @StringColumn(name = "ACTION_ID", nullable = true, type = StringType.VARCHAR)
    String actionId;

    @Comment("子功能状态")
    @NumberColumn(name = "SUB_ACTION_STATUS", nullable = true, type = NumberType.BYTE, enumClass = SubActionStatus.class, defaultValue = "1")
    Integer subActionStatus;
}
