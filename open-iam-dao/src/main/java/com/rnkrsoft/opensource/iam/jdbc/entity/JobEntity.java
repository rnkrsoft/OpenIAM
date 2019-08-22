package com.rnkrsoft.opensource.iam.jdbc.entity;

import com.rnkrsoft.framework.orm.PrimaryKey;
import com.rnkrsoft.framework.orm.PrimaryKeyStrategy;
import com.rnkrsoft.framework.orm.jdbc.*;
import com.rnkrsoft.opensource.iam.enums.JobStatus;
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
@Table(name = "JOB", prefix = "TB", suffix = "INFO")
@Comment("岗位信息表")
public class JobEntity extends BaseEntity implements Serializable {
    @Comment("岗位主键")
    @PrimaryKey(strategy = PrimaryKeyStrategy.IDENTITY)
    @NumberColumn(name = "JOB_ID", nullable = false, type = NumberType.INTEGER)
    Integer jobId;

    @Comment("岗位名称")
    @StringColumn(name = "JOB_NAME", nullable = true, type = StringType.VARCHAR)
    String jobName;

    @Comment("部门编号")
    @NumberColumn(name = "DEPARTMENT_ID", nullable = false, type = NumberType.INTEGER)
    Integer departmentId;

    @Comment("岗位状态")
    @NumberColumn(name = "JOB_STATUS", nullable = true, type = NumberType.BYTE, enumClass = JobStatus.class, defaultValue = "1")
    Integer jobStatus;
}
