package com.rnkrsoft.opensource.iam.jdbc.entity;

import com.rnkrsoft.framework.orm.PrimaryKey;
import com.rnkrsoft.framework.orm.PrimaryKeyStrategy;
import com.rnkrsoft.framework.orm.jdbc.*;
import com.rnkrsoft.opensource.iam.enums.BranchStatus;
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
@Table(name = "BRANCH", prefix = "TB", suffix = "INFO")
@Comment("分支机构")
public class BranchEntity extends BaseEntity implements Serializable {
    @PrimaryKey(strategy = PrimaryKeyStrategy.IDENTITY)
    @Comment("分支机构主键")
    @NumberColumn(name = "BRANCH_ID", nullable = false, type = NumberType.INTEGER)
    Integer branchId;

    @Comment("分支机构名称")
    @StringColumn(name = "BRANCH_NAME", nullable = true, type = StringType.VARCHAR)
    String branchName;

    @Comment("城市主键")
    @NumberColumn(name = "CITY_ID", nullable = true, type = NumberType.BYTE)
    Integer cityId;

    @Comment("分支机构状态")
    @NumberColumn(name = "BRANCH_STATUS", nullable = true, type = NumberType.BYTE, enumClass = BranchStatus.class, defaultValue = "1")
    Integer branchStatus;


}
