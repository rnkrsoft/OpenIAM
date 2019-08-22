package com.rnkrsoft.opensource.iam.jdbc.entity;

import com.rnkrsoft.framework.orm.PrimaryKey;
import com.rnkrsoft.framework.orm.PrimaryKeyStrategy;
import com.rnkrsoft.framework.orm.jdbc.*;
import lombok.*;

import java.io.Serializable;

/**
 * Created by woate on 2019/7/14.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "CHANNEL", prefix = "TB", suffix = "INFO")
@Comment("渠道")
public class ChannelEntity  extends BaseEntity implements Serializable {
    @PrimaryKey(strategy = PrimaryKeyStrategy.AUTO)
    @Comment("渠道编号")
    @StringColumn(name = "CHANNEL_NO", nullable = false, type = StringType.VARCHAR)
    String channelNo;
    @Comment("渠道名")
    @StringColumn(name = "CHANNEL_NAME", nullable = false, type = StringType.VARCHAR)
    String channelName;
}
