package com.rnkrsoft.opensource.iam.jdbc.entity;

import com.rnkrsoft.framework.orm.PrimaryKey;
import com.rnkrsoft.framework.orm.PrimaryKeyStrategy;
import com.rnkrsoft.framework.orm.jdbc.*;
import com.rnkrsoft.opensource.iam.enums.SexType;
import com.rnkrsoft.opensource.iam.enums.UserStatus;
import com.rnkrsoft.opensource.iam.enums.YesOrNo;
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
@Table(name = "USER", prefix = "TB", suffix = "INFO")
@Comment("用户信息")
public class UserEntity extends BaseEntity implements Serializable {
    @PrimaryKey(strategy = PrimaryKeyStrategy.UUID)
    @Comment("用户号")
    @StringColumn(name = "USER_ID", nullable = false, type = StringType.VARCHAR)
    String userId;

    @Comment("用户名")
    @StringColumn(name = "USER_NAME", nullable = true, type = StringType.VARCHAR)
    String userName;

    @Comment("昵称")
    @StringColumn(name = "NICK_NAME", nullable = true, type = StringType.VARCHAR)
    String nickName;

    @Comment("用户头像")
    @StringColumn(name = "USER_AVATAR", nullable = false, type = StringType.VARCHAR, defaultValue = "/avatars/default.jpg")
    String userAvatar;

    @Comment("性别")
    @NumberColumn(name = "SEX", nullable = true, type = NumberType.INTEGER, enumClass = SexType.class, defaultValue = "-1")
    Integer sex;

    @Comment("手机号")
    @StringColumn(name = "MOBILE_NO", nullable = true, type = StringType.VARCHAR)
    String mobileNo;

    @Comment("是否首次登录")
    @NumberColumn(name = "FIRST_LOGIN", nullable = true, type = NumberType.BOOLEAN, defaultValue = "1")
    Boolean firstLogin;

    @Comment("上一次登录的部门")
    @NumberColumn(name = "LATEST_DEPARTMENT_ID", nullable = true, type = NumberType.INTEGER)
    Integer latestDepartmentId;

    @Comment("状态")
    @NumberColumn(name = "USER_STATUS", nullable = true, type = NumberType.BYTE, enumClass = UserStatus.class, defaultValue = "1")
    Integer userStatus;
}
