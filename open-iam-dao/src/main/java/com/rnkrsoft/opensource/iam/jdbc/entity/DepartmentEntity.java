package com.rnkrsoft.opensource.iam.jdbc.entity;

import com.rnkrsoft.framework.orm.PrimaryKey;
import com.rnkrsoft.framework.orm.PrimaryKeyStrategy;
import com.rnkrsoft.framework.orm.jdbc.*;
import com.rnkrsoft.opensource.iam.enums.DepartmentStatus;
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
@Table(name = "DEPARTMENT", prefix = "TB", suffix = "INFO")
@Comment("部门信息")
public class DepartmentEntity extends BaseEntity implements Serializable {
    @PrimaryKey(strategy = PrimaryKeyStrategy.IDENTITY)
    @Comment("部门主键")
    @NumberColumn(name = "DEPARTMENT_ID", nullable = false, type = NumberType.INTEGER)
    Integer departmentId;

    @Comment("部门名称")
    @StringColumn(name = "DEPARTMENT_NAME", nullable = true, type = StringType.VARCHAR)
    String departmentName;

    @Comment("分支机构主键")
    @NumberColumn(name = "BRANCH_ID", nullable = true, type = NumberType.INTEGER)
    Integer branchId;

    @Comment("状态")
    @NumberColumn(name = "DEPARTMENT_STATUS", nullable = true, type = NumberType.BYTE, enumClass = DepartmentStatus.class, defaultValue = "1")
    Integer departmentStatus;

}
