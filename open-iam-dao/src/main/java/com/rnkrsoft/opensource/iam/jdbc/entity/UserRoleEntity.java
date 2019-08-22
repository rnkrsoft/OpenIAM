package com.rnkrsoft.opensource.iam.jdbc.entity;

import com.rnkrsoft.framework.orm.PrimaryKey;
import com.rnkrsoft.framework.orm.PrimaryKeyStrategy;
import com.rnkrsoft.framework.orm.jdbc.*;
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
@Table(name = "USER_ROLE", prefix = "TB", suffix = "MAPPING")
@Comment("用户角色映射")
public class UserRoleEntity extends BaseEntity implements Serializable {
    @Comment("物理主键")
    @PrimaryKey(strategy = PrimaryKeyStrategy.IDENTITY)
    @NumberColumn(name = "MAPPING_ID", nullable = false, type = NumberType.INTEGER)
    Integer mappingId;

    @Comment("用户号")
    @StringColumn(name = "USER_ID", nullable = false, type = StringType.VARCHAR)
    String userId;

    @Comment("角色编号")
    @NumberColumn(name = "ROLE_ID", nullable = false, type = NumberType.INTEGER)
    Integer roleId;
}
