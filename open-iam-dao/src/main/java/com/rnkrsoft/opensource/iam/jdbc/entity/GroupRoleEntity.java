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
@Table(name = "GROUP_ROLE", prefix = "TB", suffix = "MAPPING")
@Comment("用户组角色信息")
public class GroupRoleEntity extends BaseEntity implements Serializable {
    @Comment("物理主键")
    @PrimaryKey(strategy = PrimaryKeyStrategy.IDENTITY)
    @NumberColumn(name = "MAPPING_ID", nullable = false, type = NumberType.INTEGER)
    Integer mappingId;

    @Comment("用户组主键")
    @NumberColumn(name = "GROUP_ID", nullable = false, type = NumberType.INTEGER)
    Integer groupId;

    @Comment("角色主键")
    @NumberColumn(name = "ROLE_ID", nullable = true, type = NumberType.INTEGER)
    Integer roleId;

}
