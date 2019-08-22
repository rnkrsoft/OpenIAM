package com.rnkrsoft.opensource.iam.jdbc.entity;

import com.rnkrsoft.framework.orm.PrimaryKey;
import com.rnkrsoft.framework.orm.PrimaryKeyStrategy;
import com.rnkrsoft.framework.orm.jdbc.*;
import com.rnkrsoft.opensource.iam.enums.GroupStatus;
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
@Table(name = "GROUP", prefix = "TB", suffix = "INFO")
@Comment("用户组信息")
public class GroupEntity  extends BaseEntity implements Serializable {
    @PrimaryKey(strategy = PrimaryKeyStrategy.IDENTITY)
    @Comment("用户组主键")
    @NumberColumn(name = "GROUP_ID", nullable = false, type = NumberType.INTEGER)
    Integer groupId;

    @Comment("用户组名称")
    @StringColumn(name = "GROUP_NAME", nullable = true, type = StringType.VARCHAR)
    String groupName;

    @Comment("用户组状态")
    @NumberColumn(name = "GROUP_STATUS", nullable = true, type = NumberType.BYTE, enumClass = GroupStatus.class, defaultValue = "1")
    Integer groupStatus;

}
