package com.rnkrsoft.opensource.iam.jdbc.entity;

import com.rnkrsoft.framework.orm.PrimaryKey;
import com.rnkrsoft.framework.orm.PrimaryKeyStrategy;
import com.rnkrsoft.framework.orm.jdbc.*;
import com.rnkrsoft.opensource.iam.enums.ProductStatus;
import com.rnkrsoft.opensource.iam.enums.RoleStatus;
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
@Table(name = "ROLE", prefix = "TB", suffix = "INFO")
@Comment("角色信息")
public class RoleEntity extends BaseEntity implements Serializable {
    @PrimaryKey(strategy = PrimaryKeyStrategy.IDENTITY)
    @Comment("角色主键")
    @NumberColumn(name = "ROLE_ID", nullable = false, type = NumberType.INTEGER)
    Integer roleId;

    @Comment("角色名")
    @StringColumn(name = "ROLE_NAME", nullable = true, type = StringType.VARCHAR)
    String roleName;


    @Comment("角色状态")
    @NumberColumn(name = "ROLE_STATUS", nullable = true, type = NumberType.BYTE, enumClass = RoleStatus.class, defaultValue = "1")
    Integer roleStatus;
}
