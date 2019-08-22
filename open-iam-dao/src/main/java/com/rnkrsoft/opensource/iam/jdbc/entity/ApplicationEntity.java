package com.rnkrsoft.opensource.iam.jdbc.entity;

import com.rnkrsoft.framework.orm.PrimaryKey;
import com.rnkrsoft.framework.orm.PrimaryKeyStrategy;
import com.rnkrsoft.framework.orm.jdbc.*;
import com.rnkrsoft.opensource.iam.enums.AppStatus;
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
@Table(name = "APPLICATION", prefix = "TB", suffix = "INFO")
@Comment("应用")
public class ApplicationEntity  extends BaseEntity implements Serializable {
    @Comment("应用主键")
    @PrimaryKey(strategy = PrimaryKeyStrategy.IDENTITY)
    @NumberColumn(name = "APP_ID", nullable = false, type = NumberType.INTEGER)
    Integer appId;

    @Comment("应用代码")
    @StringColumn(name = "APP_CODE", nullable = true, type = StringType.VARCHAR)
    String appCode;

    @Comment("应用标题")
    @StringColumn(name = "APP_TITLE", nullable = true, type = StringType.VARCHAR)
    String appTitle;

    @Comment("应用链接地址")
    @StringColumn(name = "APP_URL", nullable = true, type = StringType.VARCHAR)
    String appUrl;

    @Comment("应用图片地址")
    @StringColumn(name = "APP_ICON", nullable = true, type = StringType.VARCHAR, defaultValue = "/static/icon/application.png")
    String appIcon;

    @Comment("状态")
    @NumberColumn(name = "APP_STATUS", nullable = true, type = NumberType.BYTE, enumClass = AppStatus.class, defaultValue = "1")
    Integer appStatus;

}
