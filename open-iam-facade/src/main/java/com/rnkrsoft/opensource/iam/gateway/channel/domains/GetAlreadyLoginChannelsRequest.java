package com.rnkrsoft.opensource.iam.gateway.channel.domains;

import com.rnkrsoft.platform.protocol.TokenAble;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by woate on 2019/7/14.
 */
@Data
public class GetAlreadyLoginChannelsRequest implements Serializable, TokenAble{
    String token;
}
