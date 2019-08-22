package com.rnkrsoft.opensource.iam.gateway.channel.domains;

import lombok.Data;

import javax.web.doc.annotation.ApidocElement;
import java.io.Serializable;

/**
 * Created by woate on 2019/7/14.
 */
@Data
public class GetAlreadyLoginChannelsRecord implements Serializable{
    @ApidocElement("渠道号")
    String channelNo;
    @ApidocElement("渠道名")
    String channelName;
}
