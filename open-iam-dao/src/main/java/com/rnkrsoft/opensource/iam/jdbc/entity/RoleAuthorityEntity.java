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
@Table(name = "ROLE_AUTHORITY", prefix = "TB", suffix = "MAPPING")
@Comment("角色权限映射表")
public class RoleAuthorityEntity extends BaseEntity implements Serializable {
    @Comment("物理主键")
    @PrimaryKey(strategy = PrimaryKeyStrategy.IDENTITY)
    @NumberColumn(name = "MAPPING_ID", nullable = false, type = NumberType.INTEGER)
    Integer mappingId;

    @Comment("角色主键")
    @NumberColumn(name = "ROLE_ID", nullable = false, type = NumberType.INTEGER)
    Integer roleId;

    @Comment("权限主键")
    @StringColumn(name = "AUTHORITY_ID", nullable = true, type = StringType.VARCHAR)
    String authorityId;

}
