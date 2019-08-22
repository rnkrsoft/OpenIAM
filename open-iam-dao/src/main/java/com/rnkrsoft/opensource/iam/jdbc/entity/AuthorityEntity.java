package com.rnkrsoft.opensource.iam.jdbc.entity;

import com.rnkrsoft.framework.orm.PrimaryKey;
import com.rnkrsoft.framework.orm.PrimaryKeyStrategy;
import com.rnkrsoft.framework.orm.jdbc.*;
import com.rnkrsoft.opensource.iam.enums.ResourceType;
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
@Table(name = "AUTHORITY", prefix = "TB", suffix = "INFO")
@Comment("权限")
public class AuthorityEntity  extends BaseEntity implements Serializable {
    @PrimaryKey(strategy = PrimaryKeyStrategy.IDENTITY)
    @Comment("权限主键")
    @NumberColumn(name = "AUTHORITY_ID", nullable = false, type = NumberType.INTEGER)
    Integer authorityId;

    @Comment("权限主键")
    @NumberColumn(name = "RESOURCE_TYPE", nullable = false, type = NumberType.INTEGER, enumClass = ResourceType.class)
    Integer resourceType;

    @Comment("操作主键")
    @NumberColumn(name = "RESOURCE_ID", nullable = true, type = NumberType.INTEGER)
    Integer resourceId;
}
