package com.rnkrsoft.opensource.iam.jdbc.entity;

import com.rnkrsoft.framework.orm.PrimaryKey;
import com.rnkrsoft.framework.orm.PrimaryKeyStrategy;
import com.rnkrsoft.framework.orm.jdbc.*;
import com.rnkrsoft.opensource.iam.enums.CityStatus;
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
@Table(name = "CITY", prefix = "TB", suffix = "INFO")
@Comment("城市信息")
public class CityEntity extends BaseEntity implements Serializable {
    @PrimaryKey(strategy = PrimaryKeyStrategy.IDENTITY)
    @Comment("城市主键")
    @NumberColumn(name = "CITY_ID", nullable = false, type = NumberType.INTEGER)
    Integer cityId;

    @Comment("城市名称")
    @StringColumn(name = "CITY_NAME", nullable = false, type = StringType.VARCHAR)
    String cityName;

    @Comment("状态")
    @NumberColumn(name = "CITY_STATUS", nullable = true, type = NumberType.BYTE, enumClass = CityStatus.class, defaultValue = "1")
    Integer cityStatus;

}
